package simonev.mitrais.com.simonev.dao;

import simonev.mitrais.com.simonev.model.Login;

public interface LoginDao extends BaseDao<Login, Long> {
    Login findByUserName(String username);
}
