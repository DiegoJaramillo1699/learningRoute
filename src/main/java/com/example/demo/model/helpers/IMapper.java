package com.example.demo.model.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface IMapper {

    ModelMapper modelMapper();

}
