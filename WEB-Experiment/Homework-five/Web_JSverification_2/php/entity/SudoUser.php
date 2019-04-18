<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/16
   * Time: 20:53
   */
  class SudoUser{
    private $SudoId;
    private $SudoUserName;
    private $SudoUserPassword;
    private $SudoCount;
    private $SudoUserRecLoginTime;
    /**
     * SudoUser constructor.
     * @param $SudoId
     * @param $SudoUserName
     * @param $SudoUserPassword
     * @param $SudoCount
     * @param $SudoUserRecLoginTime
     */
    public function __construct($SudoId = "", $SudoUserName = "",
                                $SudoUserPassword = "", $SudoCount = "",
                                $SudoUserRecLoginTime = ""){
      $this->SudoId = $SudoId;
      $this->SudoUserName = $SudoUserName;
      $this->SudoUserPassword = $SudoUserPassword;
      $this->SudoCount = $SudoCount;
      $this->SudoUserRecLoginTime = $SudoUserRecLoginTime;
    }

    /**
     * @return string
     */
    public function getSudoUserRecLoginTime(){
      return $this->SudoUserRecLoginTime;
    }

    /**
     * @param string $SudoUserRecLoginTime
     */
    public function setSudoUserRecLoginTime($SudoUserRecLoginTime){
      $this->SudoUserRecLoginTime = $SudoUserRecLoginTime;
    }

    /**
     * @return string
     */
    public function getSudoCount(){
      return $this->SudoCount;
    }

    /**
     * @param string $SudoCount
     */
    public function setSudoCount($SudoCount){
      $this->SudoCount = $SudoCount;
    }

    /**
     * @return mixed
     */
    public function getSudoId(){
      return $this->SudoId;
    }

    /**
     * @param mixed $SudoId
     */
    public function setSudoId($SudoId){
      $this->SudoId = $SudoId;
    }

    /**
     * @return mixed
     */
    public function getSudoUserName(){
      return $this->SudoUserName;
    }

    /**
     * @param mixed $SudoUserName
     */
    public function setSudoUserName($SudoUserName){
      $this->SudoUserName = $SudoUserName;
    }

    /**
     * @return mixed
     */
    public function getSudoUserPassword(){
      return $this->SudoUserPassword;
    }

    /**
     * @param mixed $SudoUserPassword
     */
    public function setSudoUserPassword($SudoUserPassword){
      $this->SudoUserPassword = $SudoUserPassword;
    }

  }