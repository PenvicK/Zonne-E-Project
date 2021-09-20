package br.com.zonne.api.services;

import br.com.zonne.api.models.DealershipModel;
import br.com.zonne.api.models.DistrictModel;
import br.com.zonne.api.repositories.DistrictRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {

    @TestConfiguration
    static class DistrictServiceTestConfiguration {

        @Bean
        public DistrictService districtService() {
            return new DistrictService();
        }
    }

    @Autowired
    DistrictService service;

    @MockBean
    DistrictRepository repository;

    @Test
    public void districtServiceTestFindById(){
        Long id = 1L;
        DistrictModel districtTest = service.findById(id);
        Assertions.assertEquals(districtTest.getIdDistrict(), id);
    }
    @Test
    public void districtServiceTestInsertDistrict(){
        DistrictModel districtTest = new DistrictModel(1L, "bairroTeste");
        Mockito.when(repository.save(districtTest)).thenReturn(districtTest);
        DistrictModel districtInserted = service.findById(1L);

        Assertions.assertEquals(districtTest, districtInserted);
        Assertions.assertNotNull(districtInserted);
        System.out.println(districtInserted);

    }
    @Test
    public void districtServiceTestEdit(){
        DistrictModel districtTestEdit = service.findById(1L);
        DistrictModel districtToCompare = new DistrictModel(1L,"districtTeste");

        districtTestEdit.setDistrictName("BairroTeste");

        service.edit(1L, districtTestEdit);

        DistrictModel districtEdit = new DistrictModel();

        districtEdit.setIdDistrict(districtTestEdit.getIdDistrict());
        districtEdit.setDistrictName(districtTestEdit.getDistrictName());

        Assertions.assertEquals(districtTestEdit, districtEdit);
        Assertions.assertNotEquals(districtToCompare, districtEdit);

        System.out.println(" " + districtEdit.getIdDistrict() + " " + districtEdit.getDistrictName());
        System.out.println(" " + districtToCompare.getIdDistrict() + " " + districtToCompare.getDistrictName());
    }

    @Before
    public void setup(){
        DistrictModel district = new DistrictModel(1L, "bairroTeste");

        Mockito.when(repository.findById(district.getIdDistrict())).thenReturn(java.util.Optional.of(district));

    }
}
