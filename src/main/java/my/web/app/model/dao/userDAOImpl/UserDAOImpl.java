package my.web.app.model.dao.userDAOImpl;

import my.web.app.model.entity.User;
import my.web.app.model.dao.userDAO.UserDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component(value = "userDAO")
public class UserDAOImpl implements UserDAO {

    public UserDAOImpl(){
        users.add(new User(1,"yarik","123"));
        users.add(new User(2,"user","321"));
        users.add(new User(3,"admin","admin"));
    }

    private List<User> users = new ArrayList<>();

    private AtomicInteger currentId = new AtomicInteger(3);

    public void updateUser(User user){
        for (User userC : users) {
            if (userC.getId() == user.getId()){
                userC.setLogin(user.getLogin());
                userC.setPassword(user.getPassword());
            }
        }
    }

    @Override
    public User getUserById(int id) {
        for (User user:users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getId() == id){
                iterator.remove();
            }
        }
        currentId.decrementAndGet();
    }

    @Override
    public void addUser(User user) {
        if (users.contains(user)) {
            throw new RuntimeException("Such user is already exist!");
        }
        user.setId(currentId.incrementAndGet());
        users.add(user);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    public AtomicInteger getCurrentId() {
        return currentId;
    }

}
