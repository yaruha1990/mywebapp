package my.web.app.model.service.userService;

import my.web.app.model.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    List<User> getUsers();
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
