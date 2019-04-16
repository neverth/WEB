<?php
  /**
   * Created by IntelliJ IDEA.
   * User: liyang
   * Date: 2019/4/13
   * Time: 10:36
   */
  class User{
    private $id;
    private $username;
    private $password;
    private $email;
    private $sex;
    private $birthday;
    private $hobby;

    public function __construct(
        $id = "", $username = "", $password = "", $email = "",
        $sex = "", $birthday = "", $hobby = ""){

      $this->id = $id;
      $this->username = $username;
      $this->password = $password;
      $this->email = $email;
      $this->sex = $sex;
      $this->birthday = $birthday;
      $this->hobby = $hobby;
    }

    public function getId(){
      return $this->id;
    }
    public function setId($id){
      $this->id = $id;
    }
    public function getUsername(){
      return $this->username;
    }
    public function setUsername($username){
      $this->username = $username;
    }
    public function getPassword(){
      return $this->password;
    }
    public function setPassword($password){
      $this->password = $password;
    }
    public function getEmail(){
      return $this->email;
    }
    public function setEmail($email){
      $this->email = $email;
    }
    public function getSex(){
      return $this->sex;
    }
    public function setSex($sex){
      $this->sex = $sex;
    }
    public function getBirthday(){
      return $this->birthday;
    }
    public function setBirthday($birthday){
      $this->birthday = $birthday;
    }
    public function getHobby(){
      return $this->hobby;
    }
    public function setHobby($hobby){
      $this->hobby = $hobby;
    }
  }