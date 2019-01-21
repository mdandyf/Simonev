package simonev.mitrais.com.simonev.dao;

import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simonev.mitrais.com.simonev.model.Login;

public class LoginDaoImplementation implements LoginDao {

    private String[] dummyData = new String[]{
            "foo@example.com:hello",
            "bar@example.com:world",
            "juki@gmail.com:slank",
            "user@mitrais.com:user1"
    };

    @Override
    public void insert(Login login) {
        List<String> listData = Arrays.asList(getDummyData());
        listData.add(login.getUsername() + ":" + login.getPassword());
        this.dummyData = (String[]) listData.toArray();
    }

    @Override
    public boolean update(final Login login) {
        final List<String> listData = Arrays.asList(getDummyData());
        int index = listData.indexOf(login.getUsername()+":"+login.getPassword());
        if(listData.get(index) != null) {
            listData.remove(listData.get(index));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        final List<String> listData = Arrays.asList(getDummyData());
        int index = Integer.valueOf(Long.toString(id));
        if(listData.get(index) != null) {
            listData.remove(listData.get(index));
            return true;
        }
        return false;
    }

    @Override
    public List<Login> findAll() {
        final List<String> listData = Arrays.asList(getDummyData());
        List<Login> listLogin = new ArrayList<>();
        for(String data : listData) {
            String[] pieces = data.split(":");
            Login login = new Login();
            login.setUsername(pieces[0]);
            login.setPassword(pieces[1]);
            listLogin.add(login);
        }
        return listLogin;
    }

    @Override
    public Login findById(Long aLong) {
        return null;
    }

    @Override
    public Login findByUserName(String username) {
        return null;
    }

    public String[] getDummyData() {
        return dummyData;
    }

    public void setDummyData(String[] dummyData) {
        this.dummyData = dummyData;
    }

    private int getDummyDataLength() {
        return this.dummyData.length;
    }
}
