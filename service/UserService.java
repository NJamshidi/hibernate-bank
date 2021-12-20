package session7.service;

import session7.model.entity.Update;
import session7.model.entity.User;

import java.util.List;

public interface UserService {
//   session7.model.entity.User getUserById(int id);
   User findUserByNameAndFamily(String name, String family , String nationalCode);
    void add(session7.model.entity.User user);
     List<Update> getUpdates(User user);
//    void editFirstName(User user, String newValue) ;
//
//    void editLastName(User user, String newValue) ;
//
//    public void editNationalCode(User user, String newValue);
        }
