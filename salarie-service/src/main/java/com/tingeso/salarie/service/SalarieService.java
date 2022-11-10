package com.tingeso.salarie.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.tingeso.salarie.entity.Salarie;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SalarieService {

  @Autowired
  ObjectMapper objectMapper;
  
  @Autowired
  RestTemplate restTemplate;

  public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
    return objectMapper.readValue(json, listType);
  }

  public List<Salarie> getAll() throws IOException{
    List<Salarie> salaries = jsonArrayToList(restTemplate.getForObject("http://officeRRHH-service/salaries",String.class), Salarie.class);
    return salaries;
  }

}
