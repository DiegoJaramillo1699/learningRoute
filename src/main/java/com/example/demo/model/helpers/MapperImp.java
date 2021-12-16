package com.example.demo.model.helpers;

import com.example.demo.model.entities.Imagen;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperImp implements IMapper {

    public ModelMapper modelMapper(){ return new ModelMapper(); }

}
