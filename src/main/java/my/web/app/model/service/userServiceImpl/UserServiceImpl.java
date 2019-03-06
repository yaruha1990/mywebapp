package my.web.app.model.service.userServiceImpl;

import my.web.app.model.dao.userDAO.UserDAO;
import my.web.app.model.entity.User;
import my.web.app.model.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "userService")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Autowired
    @Qualifier(value = "userDAO")
    private void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }
}
