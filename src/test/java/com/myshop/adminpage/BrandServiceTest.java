package com.myshop.adminpage;

import com.myshop.adminpage.Interface.CategoryServiceInf;
import com.myshop.adminpage.model.Brand;
import com.myshop.adminpage.model.Category;
import com.myshop.adminpage.repository.BrandRepository;
import com.myshop.adminpage.repository.CategoryRepository;
import com.myshop.adminpage.service.admin.BrandService;
import com.myshop.adminpage.service.admin.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getBrandsByCategoryId() {

        Integer categoryId = 1;

//        Category category1 = getCategory1();
        Category category1 = new Category();
        category1.setId(categoryId);
        category1.setName("Category 1");
//        List<Brand> expectedBrands = Arrays.asList(
//                new Brand(1, "Brand A", null, true, false, new LinkedHashSet<>(), new LinkedHashSet<>()),
//                new Brand(2, "Brand B", null, true, false, new LinkedHashSet<>(), new LinkedHashSet<>())
//        );

        Brand brand1 = new Brand(1, "Brand A", null, true, false, new LinkedHashSet<>(), new LinkedHashSet<>());
        Brand brand2 = new Brand(2, "Brand B", null, true, false, new LinkedHashSet<>(), new LinkedHashSet<>());

        brand1.getCategories().add(category1);
        brand2.getCategories().add(category1);

        List<Brand> expectedBrands = Arrays.asList(brand1, brand2);

        Mockito.when(brandRepository.findAllBrandsByCategoryId(category1.getId())).thenReturn(expectedBrands);
        // Act
        List<Brand> actualBrands = brandService.findBrandsByCategory(category1.getId());

        // Assert
        Assertions.assertNotNull(actualBrands);
        Assertions.assertEquals(expectedBrands.size(), actualBrands.size());
        Assertions.assertIterableEquals(expectedBrands, actualBrands);

        // Verify that the repository method is called
        Mockito.verify(brandRepository, Mockito.times(1)).findAllBrandsByCategoryId(category1.getId());

        actualBrands.forEach(brand -> System.out.println(brand.getName()));
    }

//    private static Brand getBrand1(){
//        Brand brand1 = new Brand();
//        brand1.setId(1);
//        brand1.setName("A");
//        brand1.setLogo(null);
//        brand1.setActive(true);
//        brand1.setCategories(HashSet.newHashSet(4));
//        return brand1;
//    }

    private static Category getCategory1() {
        Category rootCate1 = new Category();
        rootCate1.setId(1);
        rootCate1.setName("Laptop");
        rootCate1.setSlug("laptop");

        Category subCate1 = new Category();
        subCate1.setId(2);
        subCate1.setName("Laptop Gaming");
        subCate1.setSlug("laptop-gaming");
        subCate1.setParent(rootCate1);



        Category subCate2 = new Category();
        subCate2.setId(3);
        subCate2.setName("Laptop đồ họa");
        subCate2.setSlug("laptop-do-hoa");
        subCate2.setParent(rootCate1);

        Category subCate3 = new Category();
        subCate3.setId(4);
        subCate3.setName("Laptop văn phòng");
        subCate3.setSlug("laptop-van-phong");
        subCate3.setParent(rootCate1);

        rootCate1.setSubCategories(new LinkedHashSet<>(Arrays.asList(subCate1, subCate2, subCate3)));
        return rootCate1;
    }

}
