package session7.service;

import session7.dao.UserDao;
import session7.model.entity.Update;
import session7.model.entity.User;
import session7.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
UserDao userDao=new UserDao();
//    @Override
//    public session7.model.entity.User getEmployeeById(int id) {
//        return userDao.findById(id);
//    }

    @Override
    public User findUserByNameAndFamily(String name, String family, String nationalCode) {
        return userDao.findUserByNameAndFamily(name,family,nationalCode);
    }

    @Override
    public void add(session7.model.entity.User user) {
        userDao.add(user);
    }
    public List<Update> getUpdates(User user) {
        return userDao.getUpdatesByUserId(user.getId());
    }

//    @Override
//    public void editFirstName(User user, String newValue) {
//        userDao.updateFirstName(user.getId(), newValue);
//    }
//
//    @Override
//    public void editLastName(User user, String newValue) {
//        userDao.updateLastName(user.getId(), newValue);
//
//    }
//
//    @Override
//    public void editNationalCode(User user, String newValue) {
//        userDao.updateNationalCode(user.getId(), newValue);
//
//    }
}
