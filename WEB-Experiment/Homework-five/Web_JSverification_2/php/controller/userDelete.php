<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/18
   * Time: 18:26
   */
  require_once ("../dao/UserDao.php");

  $user = new User();
  $user->setId($_GET['userId']);

  $userDao = new UserDao();

  if($userDao->delete($user)){
    echo "删除成功，是否需要进入";
    echo "<a href='../view/sudoLogin.php'>管理员界面</a> ？";
  }

