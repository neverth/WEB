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

    /**
     * SudoUser constructor.
     * @param $SudoId
     * @param $SudoUserName
     * @param $SudoUserPassword
     */
    public function __construct($SudoId = "", $SudoUserName = "", $SudoUserPassword = ""){
      $this->SudoId = $SudoId;
      $this->SudoUserName = $SudoUserName;
      $this->SudoUserPassword = $SudoUserPassword;
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