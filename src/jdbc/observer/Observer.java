/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.observer;

/**
 * Interface Observer implémentée par la vue
 * @author cflagollet
 */
public interface Observer {
    
  public void update(WhatChanged wc);

}
