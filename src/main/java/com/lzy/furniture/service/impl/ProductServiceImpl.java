package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.NavigationChild;
import com.lzy.furniture.entity.NavigationFather;
import com.lzy.furniture.entity.Product;
import com.lzy.furniture.repository.*;
import com.lzy.furniture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private NavigationChildRepository navigationChildRepository;
    @Autowired
    private NavigationFatherRepository navigationFatherRepository;
    @Autowired
    private VisitTotleRepository visitTotleRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Product> getLastProductList(Integer num) {

        return utilProductHelper(productRepository.findLastProductListOrderByUploadTime(num));
    }

    @Override
    public List<Product> getProductListByFatherId(Integer fatherId) {
        return  utilProductHelper(productRepository.findAllByFatherIdOrderByUploadTimeDesc(fatherId));
    }

    @Override
    public List<Product> getProductListByFatherIdAndChildId(Integer fatherId, List<Integer> array) {
        return utilProductHelper(productRepository.findAllByFatherIdAndChildIdInOrderByUploadTimeDesc(fatherId, array));
    }

    @Override
    public List<Product> getProductListBySearch(String prodName) {
        return utilProductHelper(productRepository.findAllByIdContainingOrProdNameContainingOrderByUploadTimeDesc(prodName,prodName));
    }

    @Override
    public List<Product> getProductListBySearchAndChildId(List<Integer> array, String SearchInfo) {
        return utilProductHelper(productRepository.findAllByChildIdInAndIdContainingOrProdNameContainingOrderByUploadTimeDesc(array, SearchInfo,SearchInfo));
    }

    @Override
    public Product getProductViewInfo(Integer prodId) {
        return  utilProductHelper(productRepository.findById(prodId).orElse(new Product()));
    }

    @Override
    public boolean updateProductCount(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public List<Product> getHotProductList(Integer num) {
        List<Integer> list = productRepository.findHotProductList(num);
        List<Product> getProductList = productRepository.findAllByIdIn(list);

        List<Product> productList = new ArrayList<Product>();
        for (Integer prodId : list) {
            for (Product product : getProductList) {
                if (prodId.equals(product.getId())) {
                    productList.add(product);
                    break;
                }
            }
        }
        return utilProductHelper(productList);
    }


    @Override
    public List<Product> getPersonProductList(Integer num, Integer userId, String ip) {
        List<Integer> productAllIdList = productRepository.findPersonProductList();
        List<Product> productAllList1 = productRepository.findAllByIdIn(productAllIdList);
        List<Product> productAllList = new ArrayList<Product>();

        for (Integer prodAllId : productAllIdList) {
            for (Product productAll : productAllList1) {
                if (prodAllId.equals(productAll.getId())) {
                    productAllList.add(productAll);
                    break;
                }
            }
        }

        List<Integer> productPersonIdList = new ArrayList<Integer>();

        if (userId != null) {
            productPersonIdList = productRepository.findPersonProductListByUserId(userId);
        } else {
            productPersonIdList = productRepository.findPersonProductListByIP(ip);
        }

        if (productPersonIdList.size() > 0) {
            List<Product> productPersonList1 = productRepository.findAllByIdIn(productPersonIdList);
            List<Product> productPersonList = new ArrayList<Product>();

            for (Integer prodId : productPersonIdList) {
                for (Product product : productPersonList1) {
                    if (product.getId().equals(prodId)) {
                        productPersonList.add(product);
                        break;
                    }
                }
            }

            List<Integer> prodChildIdList = new ArrayList<Integer>();
            for (Product product : productPersonList) {
                prodChildIdList.add(product.getChildId());
            }


            int length = 0, i = 0;

            if(prodChildIdList.size() >0 ){
                if ((num - productPersonIdList.size()) % prodChildIdList.size() > 0) {
                    length = 1 + (num - productPersonIdList.size()) / prodChildIdList.size();
                } else {
                    length = (num - productPersonIdList.size()) / prodChildIdList.size();
                }
            }


            for (Integer prodChildId : prodChildIdList) {
                for (Product productLike : productAllList) {
                    if (productLike.getChildId().equals(prodChildId) && !productPersonIdList.contains(productLike.getId())) {
                        productPersonIdList.add(productLike.getId());
                        productPersonList.add(productLike);
                        if (productPersonIdList.size() >= num || ++i >= length) {
                            i = 0;
                            break;
                        }
                    }
                }
            }

            if (productPersonList.size() < num) {
                for (Product productLike : productAllList) {
                    if (!productPersonIdList.contains(productLike.getId())) {
                        productPersonList.add(productLike);
                        productPersonIdList.add(productLike.getId());
                        if (productPersonIdList.size() >= num) {
                            break;
                        }
                    }
                }
            }
            return  utilProductHelper(productPersonList);
        } else if (productAllList.size() < num) {
            return  utilProductHelper(productAllList);
        } else {
            return  utilProductHelper(productAllList.subList(0, num));
        }

    }


    @Override
    public List<Product> getAllProductOrderTime() {
        List<Product> productList = productRepository.findAllOrderByUploadTimeDesc();

        List<NavigationChild> navigationChildList = navigationChildRepository.findAll();

        List<NavigationFather> navigationFatherList = navigationFatherRepository.findAll();

        List<Product> list = new ArrayList<Product>();

        for (Product product : productList) {
            for (NavigationChild navigationChild : navigationChildList) {
                if (product.getChildId().equals(navigationChild.getId())) {
                    product.setChildName(navigationChild.getNaviName());
                    break;
                }
            }
            for (NavigationFather navigationFather : navigationFatherList) {
                if (product.getFatherId().equals(navigationFather.getId())) {
                    product.setFatherName(navigationFather.getNaviName());
                    break;
                }
            }
            list.add(product);
        }

        return  utilProductHelper(list);
    }

    @Override
    public boolean saveProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteProduct(Integer prodId) {
        productRepository.deleteById(prodId);
        visitTotleRepository.deleteByProdId(prodId);
        cartRepository.deleteByProdId(prodId);

        return true;
    }

    private List<Product> utilProductHelper(List<Product> productList) {
        String filename1 = "";
        String filename2 = "";
        String filename3 = "";
        String filename4 = "";
        String filename5 = "";
        String filename6 = "";
        String filename7 = "";
        String filename8 = "";
        String filenameUrl1 = "";
        String filenameUrl2 = "";
        String filenameUrl3 = "";
        String filenameUrl4 = "";
        String filenameUrl5 = "";
        String filenameUrl6 = "";
        String filenameUrl7 = "";
        String filenameUrl8 = "";
        String fileBaseName1 = "";
        String fileBaseName2 = "";
        String fileBaseName3 = "";
        String fileBaseName4 = "";
        String fileBaseName5 = "";
        String fileBaseName6 = "";
        String fileBaseName7 = "";
        String fileBaseName8 = "";
        String fileType1 = "";
        String fileType2 = "";
        String fileType3 = "";
        String fileType4 = "";
        String fileType5 = "";
        String fileType6 = "";
        String fileType7 = "";
        String fileType8 = "";
        for (Product product : productList) {
            if (product.getAddress1() != null && product.getAddress1().length() > 0) {
                filename1 = product.getAddress1().substring(product.getAddress1().lastIndexOf("/") + 1);
                filenameUrl1 = product.getAddress1().substring(0, product.getAddress1().length() - filename1.length() - 1);
                fileType1 = filename1.substring(filename1.lastIndexOf(".") + 1);
                fileBaseName1 = filename1.substring(0, filename1.length() - fileType1.length() - 1);
                product.setAddressMini1(filenameUrl1 +"/"+ fileBaseName1 + "_mini." + fileType1);
                product.setAddressMiddle1(filenameUrl1 +"/"+ fileBaseName1 + "_middle." + fileType1);
                product.setAddressLarge1(filenameUrl1 +"/"+ fileBaseName1 + "_large." + fileType1);
            }
            if (product.getAddress2() != null && product.getAddress2().length() > 0) {
                filename2 = product.getAddress2().substring(product.getAddress2().lastIndexOf("/") + 1);
                filenameUrl2 = product.getAddress2().substring(0, product.getAddress2().length() - filename2.length() - 1);
                fileType2 = filename2.substring(filename2.lastIndexOf(".") + 1);
                fileBaseName2 = filename2.substring(0, filename2.length() - fileType2.length() - 1);
                product.setAddressMini2(filenameUrl2 +"/"+ fileBaseName2 + "_mini." + fileType2);
                product.setAddressMiddle2(filenameUrl2 +"/"+ fileBaseName2 + "_middle." + fileType2);
                product.setAddressLarge2(filenameUrl2 +"/"+ fileBaseName2 + "_large." + fileType2);
            }
            if (product.getAddress3() != null && product.getAddress3().length() > 0) {
                filename3 = product.getAddress3().substring(product.getAddress3().lastIndexOf("/") + 1);
                filenameUrl3 = product.getAddress3().substring(0, product.getAddress3().length() - filename3.length() - 1);
                fileType3 = filename3.substring(filename3.lastIndexOf(".") + 1);
                fileBaseName3 = filename3.substring(0, filename3.length() - fileType3.length() - 1);
                product.setAddressMini3(filenameUrl3 +"/"+ fileBaseName3 + "_mini." + fileType3);
                product.setAddressMiddle3(filenameUrl3 +"/"+ fileBaseName3+ "_middle." + fileType3);
                product.setAddressLarge3(filenameUrl3 +"/"+ fileBaseName3 + "_large." + fileType3);
            }
            if (product.getAddress4() != null && product.getAddress4().length() > 0) {
                filename4 = product.getAddress4().substring(product.getAddress4().lastIndexOf("/") + 1);
                filenameUrl4 = product.getAddress4().substring(0, product.getAddress4().length() - filename4.length() - 1);
                fileType4 = filename4.substring(filename4.lastIndexOf(".") + 1);
                fileBaseName4 = filename4.substring(0, filename4.length() - fileType4.length() - 1);
                product.setAddressMini4(filenameUrl4 +"/"+ fileBaseName4 + "_mini." + fileType4);
                product.setAddressMiddle4(filenameUrl4 +"/"+ fileBaseName4 + "_middle." + fileType4);
                product.setAddressLarge4(filenameUrl4 +"/"+ fileBaseName4 + "_large." + fileType4);
            }
            if (product.getAddress5() != null && product.getAddress5().length() > 0) {
                filename5 = product.getAddress5().substring(product.getAddress5().lastIndexOf("/") + 1);
                filenameUrl5 = product.getAddress5().substring(0, product.getAddress5().length() - filename5.length() - 1);
                fileType5 = filename5.substring(filename5.lastIndexOf(".") + 1);
                fileBaseName5 = filename5.substring(0, filename5.length() - fileType5.length() - 1);
                product.setAddressMini5(filenameUrl5 +"/"+ fileBaseName5 + "_mini." + fileType5);
                product.setAddressMiddle5(filenameUrl5 +"/"+ fileBaseName5 + "_middle." + fileType5);
                product.setAddressLarge5(filenameUrl5 +"/"+ fileBaseName5 + "_large." + fileType5);
            }
            if (product.getAddress6() != null && product.getAddress6().length() > 0) {
                filename6 = product.getAddress6().substring(product.getAddress6().lastIndexOf("/") + 1);
                filenameUrl6 = product.getAddress6().substring(0, product.getAddress6().length() - filename6.length() - 1);
                fileType6 = filename6.substring(filename6.lastIndexOf(".") + 1);
                fileBaseName6 = filename6.substring(0, filename6.length() - fileType6.length() - 1);
                product.setAddressMini6(filenameUrl6 +"/"+ fileBaseName6 + "_mini." + fileType6);
                product.setAddressMiddle6(filenameUrl6 +"/"+ fileBaseName6 + "_middle." + fileType6);
                product.setAddressLarge6(filenameUrl6 +"/"+ fileBaseName6 + "_large." + fileType6);
            }
            if (product.getAddress7() != null && product.getAddress7().length() > 0) {
                filename7 = product.getAddress7().substring(product.getAddress7().lastIndexOf("/") + 1);
                filenameUrl7 = product.getAddress7().substring(0, product.getAddress7().length() - filename7.length() - 1);
                fileType7 = filename7.substring(filename7.lastIndexOf(".") + 1);
                fileBaseName7 = filename7.substring(0, filename7.length() - fileType7.length() - 1);
                product.setAddressMini7(filenameUrl7 +"/"+ fileBaseName7 + "_mini." + fileType7);
                product.setAddressMiddle7(filenameUrl7 +"/"+ fileBaseName7 + "_middle." + fileType7);
                product.setAddressLarge7(filenameUrl7 +"/"+ fileBaseName7 + "_large." + fileType7);
            }
            if (product.getAddress8() != null && product.getAddress8().length() > 0) {
                filename8 = product.getAddress8().substring(product.getAddress8().lastIndexOf("/") + 1);
                filenameUrl8 = product.getAddress8().substring(0, product.getAddress8().length() - filename8.length() - 1);
                fileType8 = filename8.substring(filename8.lastIndexOf(".") + 1);
                fileBaseName8 = filename8.substring(0, filename8.length() - fileType8.length() - 1);
                product.setAddressMini8(filenameUrl8 +"/"+ fileBaseName8 + "_mini." + fileType8);
                product.setAddressMiddle8(filenameUrl8 +"/"+ fileBaseName8 + "_middle." + fileType8);
                product.setAddressLarge8(filenameUrl8 +"/"+ fileBaseName8 + "_large." + fileType8);
            }
        }
        return productList;
    }


    private Product utilProductHelper(Product product) {
        String filename1 = "";
        String filename2 = "";
        String filename3 = "";
        String filename4 = "";
        String filename5 = "";
        String filename6 = "";
        String filename7 = "";
        String filename8 = "";
        String filenameUrl1 = "";
        String filenameUrl2 = "";
        String filenameUrl3 = "";
        String filenameUrl4 = "";
        String filenameUrl5 = "";
        String filenameUrl6 = "";
        String filenameUrl7 = "";
        String filenameUrl8 = "";
        String fileBaseName1 = "";
        String fileBaseName2 = "";
        String fileBaseName3 = "";
        String fileBaseName4 = "";
        String fileBaseName5 = "";
        String fileBaseName6 = "";
        String fileBaseName7 = "";
        String fileBaseName8 = "";
        String fileType1 = "";
        String fileType2 = "";
        String fileType3 = "";
        String fileType4 = "";
        String fileType5 = "";
        String fileType6 = "";
        String fileType7 = "";
        String fileType8 = "";
        if (product.getAddress1() != null && product.getAddress1().length() > 0) {
            filename1 = product.getAddress1().substring(product.getAddress1().lastIndexOf("/") + 1);
            filenameUrl1 = product.getAddress1().substring(0, product.getAddress1().length() - filename1.length() - 1);
            fileType1 = filename1.substring(filename1.lastIndexOf(".") + 1);
            fileBaseName1 = filename1.substring(0, filename1.length() - fileType1.length() - 1);
            product.setAddressMini1(filenameUrl1 +"/"+ fileBaseName1 + "_mini." + fileType1);
            product.setAddressMiddle1(filenameUrl1 +"/"+ fileBaseName1 + "_middle." + fileType1);
            product.setAddressLarge1(filenameUrl1 +"/"+ fileBaseName1 + "_large." + fileType1);
        }
        if (product.getAddress2() != null && product.getAddress2().length() > 0) {
            filename2 = product.getAddress2().substring(product.getAddress2().lastIndexOf("/") + 1);
            filenameUrl2 = product.getAddress2().substring(0, product.getAddress2().length() - filename2.length() - 1);
            fileType2 = filename2.substring(filename2.lastIndexOf(".") + 1);
            fileBaseName2 = filename2.substring(0, filename2.length() - fileType2.length() - 1);
            product.setAddressMini2(filenameUrl2 +"/"+ fileBaseName2 + "_mini." + fileType2);
            product.setAddressMiddle2(filenameUrl2 +"/"+ fileBaseName2 + "_middle." + fileType2);
            product.setAddressLarge2(filenameUrl2 +"/"+ fileBaseName2 + "_large." + fileType2);
        }
        if (product.getAddress3() != null && product.getAddress3().length() > 0) {
            filename3 = product.getAddress3().substring(product.getAddress3().lastIndexOf("/") + 1);
            filenameUrl3 = product.getAddress3().substring(0, product.getAddress3().length() - filename3.length() - 1);
            fileType3 = filename3.substring(filename3.lastIndexOf(".") + 1);
            fileBaseName3 = filename3.substring(0, filename3.length() - fileType3.length() - 1);
            product.setAddressMini3(filenameUrl3 +"/"+ fileBaseName3 + "_mini." + fileType3);
            product.setAddressMiddle3(filenameUrl3 +"/"+ fileBaseName3+ "_middle." + fileType3);
            product.setAddressLarge3(filenameUrl3 +"/"+ fileBaseName3 + "_large." + fileType3);
        }
        if (product.getAddress4() != null && product.getAddress4().length() > 0) {
            filename4 = product.getAddress4().substring(product.getAddress4().lastIndexOf("/") + 1);
            filenameUrl4 = product.getAddress4().substring(0, product.getAddress4().length() - filename4.length() - 1);
            fileType4 = filename4.substring(filename4.lastIndexOf(".") + 1);
            fileBaseName4 = filename4.substring(0, filename4.length() - fileType4.length() - 1);
            product.setAddressMini4(filenameUrl4 +"/"+ fileBaseName4 + "_mini." + fileType4);
            product.setAddressMiddle4(filenameUrl4 +"/"+ fileBaseName4 + "_middle." + fileType4);
            product.setAddressLarge4(filenameUrl4 +"/"+ fileBaseName4 + "_large." + fileType4);
        }
        if (product.getAddress5() != null && product.getAddress5().length() > 0) {
            filename5 = product.getAddress5().substring(product.getAddress5().lastIndexOf("/") + 1);
            filenameUrl5 = product.getAddress5().substring(0, product.getAddress5().length() - filename5.length() - 1);
            fileType5 = filename5.substring(filename5.lastIndexOf(".") + 1);
            fileBaseName5 = filename5.substring(0, filename5.length() - fileType5.length() - 1);
            product.setAddressMini5(filenameUrl5 +"/"+ fileBaseName5 + "_mini." + fileType5);
            product.setAddressMiddle5(filenameUrl5 +"/"+ fileBaseName5 + "_middle." + fileType5);
            product.setAddressLarge5(filenameUrl5 +"/"+ fileBaseName5 + "_large." + fileType5);
        }
        if (product.getAddress6() != null && product.getAddress6().length() > 0) {
            filename6 = product.getAddress6().substring(product.getAddress6().lastIndexOf("/") + 1);
            filenameUrl6 = product.getAddress6().substring(0, product.getAddress6().length() - filename6.length() - 1);
            fileType6 = filename6.substring(filename6.lastIndexOf(".") + 1);
            fileBaseName6 = filename6.substring(0, filename6.length() - fileType6.length() - 1);
            product.setAddressMini6(filenameUrl6 +"/"+ fileBaseName6 + "_mini." + fileType6);
            product.setAddressMiddle6(filenameUrl6 +"/"+ fileBaseName6 + "_middle." + fileType6);
            product.setAddressLarge6(filenameUrl6 +"/"+ fileBaseName6 + "_large." + fileType6);
        }
        if (product.getAddress7() != null && product.getAddress7().length() > 0) {
            filename7 = product.getAddress7().substring(product.getAddress7().lastIndexOf("/") + 1);
            filenameUrl7 = product.getAddress7().substring(0, product.getAddress7().length() - filename7.length() - 1);
            fileType7 = filename7.substring(filename7.lastIndexOf(".") + 1);
            fileBaseName7 = filename7.substring(0, filename7.length() - fileType7.length() - 1);
            product.setAddressMini7(filenameUrl7 +"/"+ fileBaseName7 + "_mini." + fileType7);
            product.setAddressMiddle7(filenameUrl7 +"/"+ fileBaseName7 + "_middle." + fileType7);
            product.setAddressLarge7(filenameUrl7 +"/"+ fileBaseName7 + "_large." + fileType7);
        }
        if (product.getAddress8() != null && product.getAddress8().length() > 0) {
            filename8 = product.getAddress8().substring(product.getAddress8().lastIndexOf("/") + 1);
            filenameUrl8 = product.getAddress8().substring(0, product.getAddress8().length() - filename8.length() - 1);
            fileType8 = filename8.substring(filename8.lastIndexOf(".") + 1);
            fileBaseName8 = filename8.substring(0, filename8.length() - fileType8.length() - 1);
            product.setAddressMini8(filenameUrl8 +"/"+ fileBaseName8 + "_mini." + fileType8);
            product.setAddressMiddle8(filenameUrl8 +"/"+ fileBaseName8 + "_middle." + fileType8);
            product.setAddressLarge8(filenameUrl8 +"/"+ fileBaseName8 + "_large." + fileType8);
        }
        return product;
    }
}
