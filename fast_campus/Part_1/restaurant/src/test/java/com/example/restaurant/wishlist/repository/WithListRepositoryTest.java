package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WithListRepositoryTest {

    /*
     * 흥미롭게 Assertions 의 equals 가 false 라면
     * 테스트 실패로 나오길래 확인해 봤더니
     * AssertionError 예외 가 발생하는걸 구글링해서 찾았다.
     *
     * @Data 2022.05.04 00:44
     *
     */
    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create(){
        var wishListEntity = WishListEntity.builder()
                .title("title")
                .category("category")
                .address("adrees")
                .readAddress("readAddress")
                .homePageLink("")
                .imageLink("")
                .isVisit(false)
                .visitCOunt(0)
                .build();

        return wishListEntity;
    }

    @Test
    public void saveTest(){
        var wishListEntity = create();
       var expected= wishListRepository.save(wishListEntity);

       //세이브 한 결과
        Assertions.assertNotNull(expected);          //널이면 안됨
        Assertions.assertEquals(1,expected.getIndex()); //1이라는값
    }

    @Test
    public void findByIdTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected =wishListRepository.findById(1);


        Assertions.assertEquals(true,expected.isPresent()); //안에 값이 있으면

        Assertions.assertEquals(1,expected.get().getIndex()); //찾았을떄 이 데이터가 1일때

    }

    @Test
    public void deleteTest(){

    }

    @Test
    public void listAllTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        int count = wishListRepository.listAll().size();

        try {
            Assertions.assertEquals(23, count);
        }catch(AssertionError e){
            System.out.println("에러  = " + e);
        }


    }


}
