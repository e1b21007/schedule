package oit.is.team7.schedule.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import oit.is.team7.schedule.model.AccountRepository;
import oit.is.team7.schedule.model.Account;
import oit.is.team7.schedule.model.AccountMapper;
import oit.is.team7.schedule.model.UserMapper;
import oit.is.team7.schedule.model.Users;

@Service
public class AccountUserDetailsService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private AccountMapper accountMapper;

  @Autowired
  public AccountUserDetailsService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Account account = accountRepository.findByUserName(userName);

    if (account == null) {
      Users user = userMapper.selectByname(userName);
      if (user == null) {
        throw new UsernameNotFoundException("User not found with username: " + userName);
      }
      System.out.println("!!!!" + user.getUserName() + "---" + user.getPassword());
      accountMapper.InsertAccount(user.getUserName(), user.getPassword());
      account = accountRepository.findByUserName(userName);
    }

    return new User(account.getUserName(), account.getPassword(), Collections.emptyList());
  }
}
