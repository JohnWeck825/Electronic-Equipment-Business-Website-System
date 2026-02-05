package com.myshop.adminpage;

import com.myshop.adminpage.Interface.CategoryServiceInf;
import com.myshop.adminpage.model.Category;
import com.myshop.adminpage.repository.CategoryRepository;
import com.myshop.adminpage.service.admin.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryServiceInf;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListHierarchicalCategoriesInForm() {
        Category rootCate1 = getCategory1();

        Category rootCate2 = getCategory2();
//        rootCate2.setId(4);
//        rootCate2.setName("Root Category 2");
//        rootCate2.setSlug("root-category-2");

        when(categoryRepository.findAllRootCategories()).thenReturn(Arrays.asList(rootCate1, rootCate2));

        List<Category> hierarchicalCategories = categoryServiceInf.listHierarchicalCategoriesInForm();

        assertEquals(8, hierarchicalCategories.size());
//        assertEquals("Root Category 1", hierarchicalCategories.get(0).getName());
//        assertEquals("--Sub Category 1", hierarchicalCategories.get(1).getName());
//        assertEquals("--Sub Category 2", hierarchicalCategories.get(2).getName());
//        assertEquals("Root Category 2", hierarchicalCategories.get(3).getName());

        hierarchicalCategories.forEach(category -> System.out.println(category.getName()));
    }

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

    private static Category getCategory2() {
        Category rootCate2 = new Category();
        rootCate2.setId(5);
        rootCate2.setName("Điện thoại");
        rootCate2.setSlug("dien-thoai");

        Category subCate1 = new Category();
        subCate1.setName("Oppo");
        subCate1.setId(6);
        subCate1.setSlug("oppo");
        subCate1.setParent(rootCate2);

        Category subCate2 = new Category();
        subCate2.setName("Vivo");
        subCate2.setId(7);
        subCate2.setSlug("vivo");
        subCate2.setParent(rootCate2);

        Category subCate3 = new Category();
        subCate3.setName("Realme");
        subCate3.setId(8);
        subCate3.setSlug("realme");
        subCate3.setParent(rootCate2);

//        Category subCate4 = new Category();
//        subCate4.setId(9);
//        subCate4.setName("Vivo V5");
//        subCate4.setSlug("vivo-v5");
//        subCate4.setParent(subCate2);

        rootCate2.setSubCategories(new LinkedHashSet<>(Arrays.asList(subCate1, subCate2, subCate3)));
//        subCate2.setSubCategories(new LinkedHashSet<>(Arrays.asList(subCate4)));
        return rootCate2;
    }
}
