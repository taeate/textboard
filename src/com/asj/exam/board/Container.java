package com.asj.exam.board;

import java.util.Scanner;

public class Container {
    static Scanner sc;
    static UsrArticlesController usrArticlesController;
   static {
       sc = new Scanner(System.in);
       usrArticlesController = new UsrArticlesController();
   }
}
