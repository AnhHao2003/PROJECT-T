package com.manage.project.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.manage.project.model.NhacNho;

public interface NhacNhoRepo extends CrudRepository<NhacNho, Integer> {
    // Find reminders by user ID
    List<NhacNho> findByMaNguoiDung(Integer maNguoiDung);
}
