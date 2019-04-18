<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/18
   * Time: 18:31
   */
  require_once ("../dao/UserDao.php");

  $user = new User();
  $user->setId($_GET['userId']);
  $user->setUsername($_POST["username"]);
  $user->setPassword($_POST["password"]);
  $user->setEmail($_POST["email"]);
  $user->setSex($_POST["sex"] == 1 ? "男" : "女");
  $user->setBirthday($_POST["birthday"]);
  $hobbys = "";
  if ($_POST["hobby1"])
    $hobbys .= " 篮球 ";
  if($_POST["hobby2"])
    $hobbys .= " 足球 ";
  if($_POST["hobby3"])
    $hobbys .= " 养花 ";
  $user->setHobby($hobbys);

  $userDao = new UserDao();

  if ($userDao->update($user)){
    echo "修改成功，是否需要进入";
    echo "<a href='../view/sudoLogin.php'>管理员界面</a> ？";
  }