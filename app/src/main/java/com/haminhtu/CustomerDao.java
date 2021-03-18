package com.haminhtu;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CustomerDao {
    @Insert(onConflict = REPLACE)
    void insertCustomer(CustomerEntity customerEntity);

    @Update
    void updateCustomer(CustomerEntity customerEntity);

    @Delete
    void deleteCustomer(CustomerEntity customerEntity);


    @Query("SELECT * FROM Customer")
    List<CustomerEntity> getAllBookmark();

    @Query("SELECT * FROM Customer WHERE id = :id")
    CustomerEntity getBookmark(int id);

    @Query("DELETE  FROM Customer")
    void deleteAll();


}
