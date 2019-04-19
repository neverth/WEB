<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/18
   * Time: 22:37
   */
  require_once ("../dao/UserDao.php");

  if($_GET['category'] != "register"){
    Utils::loginJudgement("../view/sudoLogin.php");
  }

  $user = new User();
  $userDao = new UserDao();

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

  if($_GET['category'] == "delete"){
    $user->setId($_GET['userId']);

    if($userDao->delete($user)){

      Utils::outputAlert('删除成功');
    }
  }
  else if ($_GET['category'] == "update"){
    $user->setId($_GET['userId']);

    if ($userDao->update($user)){
      Utils::outputAlert('修改成功');
    }
  }
  else if ($_GET['category'] == "register"){
    if ($userDao->insert($user)){

      Utils::outputAlert('注册成功', "../../");
    }
  }