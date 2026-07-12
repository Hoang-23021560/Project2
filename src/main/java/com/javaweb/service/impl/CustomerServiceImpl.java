package com.javaweb.service.impl;

import com.javaweb.model.CustomerRequest;
import com.javaweb.model.CustomerSearchRequest;
import com.javaweb.model.CustomerResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.CustomerService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CustomerResponse> findALl(CustomerSearchRequest request) {
        List<CustomerEntity> customerEntities = customerRepository.findAll(request);
        List<CustomerResponse> result = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerResponse response = new CustomerResponse();
            response.setId(customerEntity.getId());
            response.setEmail(customerEntity.getEmail());
            response.setPhone(customerEntity.getPhone());
            response.setName(customerEntity.getName());
            response.setManagerStaffCustomer(
                    customerEntity.getUsers()
                            .stream()
                            .map(UserEntity::getUserName)  //Với mỗi UserEntity, lấy thuộc tính userName.Nó tương đương với:.map(user -> user.getUserName())
                            .toList()
            );
            result.add(response);
        }
        return result;
    }

    @Override
    @Transactional
    public void createOrUpdate(CustomerRequest request) {
        CustomerEntity customer;
        if(request.getId() != null){
           customer = customerRepository.findById(request.getId());
            if(customer == null){
                throw  new RuntimeException("khach hang khong ton tai");
            }
        }
        else{
            customer = new CustomerEntity();
        }
        modelMapper.map(request,customer);
        if(request.getId() != null){
            for (UserEntity user : new ArrayList<>(customer.getUsers())) {
                user.getCustomers().remove(customer);
            }

            customer.getUsers().clear();
        }
        if(request.getStaffIds() != null && request.getStaffIds().size() != 0){
            List<UserEntity> users = customerRepository.findById(request.getStaffIds());//Lấy các đối tượng UserEntity tương ứng với danh sách staffIds mà client gửi lên.
            for(UserEntity user : users){
                user.getCustomers().add(customer);
            }
            customer.setUsers(users);//Gán danh sách các nhân viên quản lý cho khách hàng.
        }

        if(request.getId() != null){
            customerRepository.updateCustomer(customer);

        }
        else{
            customerRepository.createCustomer(customer);
        }
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
            customerRepository.deleteCustomer(id);
    }
}
