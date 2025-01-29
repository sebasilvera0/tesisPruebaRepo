/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.inavi.backend.user.exception;

public class NotFound extends RuntimeException {
    public NotFound(String resourceName, Integer resourceId) {
        super(resourceName + " with ID " + resourceId + " not found");
    }
    
    public NotFound(String resourceName, String resourceId) {
        super(resourceName + " with ID " + resourceId + " not found");
    }
}

