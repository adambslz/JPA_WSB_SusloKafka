package service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private VisitDao visitDao;

    @Test
    @Transactional
    public void testShouldRemovePatient() {
        PatientTO patient = patientService.findById(1L);
        List<VisitEntity> visitsBefore = patientBefore.getVisits();
        List<DoctorEntity> doctorsBefore = new ArrayList<>();
        for(VisitEntity v: visitsBefore){
            doctorsBefore.add(v.getDoctor());
        }
    }

}
