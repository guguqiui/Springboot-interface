package cn.edu.bupt.springsecurity.userdetails.demo.service;

import cn.edu.bupt.springsecurity.userdetails.demo.bo.CustomUserDetails;
import cn.edu.bupt.springsecurity.userdetails.demo.entity.Authorities;
import cn.edu.bupt.springsecurity.userdetails.demo.entity.Users;
import cn.edu.bupt.springsecurity.userdetails.demo.mapper.AuthoritiesMapper;
import cn.edu.bupt.springsecurity.userdetails.demo.mapper.UsersMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserService implements UserDetailsService {
    @Resource
    UsersMapper usersMapper;

    @Resource
    AuthoritiesMapper authoritiesMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //selectById是按主键搜索，用户表的主键就是username
        Users user = usersMapper.selectById(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //查找当前用户名对应的权限列表
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        List<Authorities> authoritiesList = authoritiesMapper.selectByMap(map);

        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        for(Authorities authorities:authoritiesList){
            auths.add(new SimpleGrantedAuthority(authorities.getAuthority()));
        }
        //构造出返回的用户详情对象
        CustomUserDetails customUserDetails = new CustomUserDetails(user, auths);
        return customUserDetails;
    }
}
