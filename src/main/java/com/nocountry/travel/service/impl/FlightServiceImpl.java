package com.nocountry.travel.service.impl;

import com.nocountry.travel.dto.FlightDTO;
import com.nocountry.travel.dto.FlightPassengerDTO;
import com.nocountry.travel.dto.PassengerDTO;
import com.nocountry.travel.entities.Flight;
import com.nocountry.travel.entities.Passenger;
import com.nocountry.travel.exception.ParamNotFound;
import com.nocountry.travel.mapper.FlightMapper;
import com.nocountry.travel.mapper.FlightPassengerMapper;
import com.nocountry.travel.mapper.PassengerMapper;
import com.nocountry.travel.repositories.FlightRepository;
import com.nocountry.travel.repositories.PassengerRepository;
import com.nocountry.travel.service.FlightService;

import com.nocountry.travel.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{
    //private Amadeus amadeus= Amadeus.builder("1LhKbMLPrDTIY1rsScP2KflzOuBfxJJd", "0sbhrX8Qbm6LyUFu").build();

    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private FlightPassengerMapper flightPassengerMapper;

    /*@Override
    public FlightOfferSearch[] searchFlight(String origin, String destination, String departDate, String adults, String returnDate) throws ResponseException {

       System.out.println( amadeus.shopping.flightOffersSearch.get(Params.with("originLocationCode", origin)
                .and("destinationLocationCode", destination)
                .and("departureDate", departDate)
                .and("returnDate", returnDate)
                .and("adults", adults)
                .and("max",5))[0]);
       return null;
    }*/

    @Override
    public FlightDTO save(FlightDTO flightDTO) {
        Flight flight= flightMapper.flightDtoToFlight(flightDTO);
        Flight flightSaved= flightRepository.save(flight);
        return flightMapper.flight2FlightDTO(flightSaved);
    }

    @Override
    public FlightDTO update(FlightDTO flightDTO, String id){
        return flightMapper.flightUpdate2DTO(id, flightDTO);
    }

    @Override
    public void deleteFlight(String id) {
        if (id!=null){
            Optional<Flight> flight= flightRepository.findById(id);
            if (!(flight.isPresent())){
                throw new ParamNotFound("No existe el vuelo con el id solicitado: "+id);
            }else{
                flightRepository.deleteById(id);
            }
        }else{
            throw new ParamNotFound("El id es nulo o vacio");
        }
    }

    @Override
    public List<FlightDTO> listAll() {
        return flightMapper.flightList2FlightDTOList(flightRepository.findAll());
    }

    @Override
    public Flight getById(String id){
        if (!(id ==null)){
            Optional<Flight> optionalFlight= flightRepository.findById(id);
            if (!optionalFlight.isPresent()){
                throw new ParamNotFound("No existe un vuelo con el id solicitado: "+id);
            }else{
                return optionalFlight.get();
            }
        }else{
            throw new ParamNotFound("El id es nulo o vacio");
        }
    }

    @Override
    public Page<Flight> getAllPage(Pageable pageable) {
        return flightRepository.findAllPage(pageable);
    }

   /* @Override
    public List<FlightPassengerDTO> addPassengersAndFlights(String id, List<PassengerDTO> passengerDTOS) {
        System.out.println("ENTRO EN EL METODO");
        Flight flight= this.getById(id);
        System.out.println("Encontro el flight con ID: "+id);
        List<FlightPassengerDTO> flightPassengerDTOS= new ArrayList<>();
        System.out.println("Se creo la lista VACIA de FlightPassengerDto a retornar ");
       // List<Passenger> passengers= new ArrayList<>();
        //passengerDTOS.stream().forEach(passengerDTO -> passengers.add(passengerMapper.passengerDTO2Passenger(passengerDTO)));
        for (PassengerDTO dto:passengerDTOS) {
            System.out.println("EL PRIMER PASAJERO ES: "+dto.toString());
            Passenger passenger= passengerMapper.passengerDTO2Passenger(dto);
            passenger.setFlight(flight);
            System.out.println("Se agrego el vuelo al pasajero");
            Passenger passengerSaved= passengerRepository.save(passenger);
            System.out.println("Se guardo el pasajero: "+passenger.toString());
            flight.addPassenger(passenger);
            Flight flight1= flightRepository.save(flight);
            //passengerService.addFlight(id, passenger.getDocumentNumber());
            flightPassengerDTOS.add(flightPassengerMapper.flightAndPassenger2DTO(flight1, passenger));
        }
        return flightPassengerDTOS;
    }*/

    /*@Override
    public FlightDTO jsonToDto(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        String departDate = null;
        String originLocation = null;
        String destinationLocation = null;
        String cabin = null;
        Float price = null;
        for (JsonElement obj : jsonArray) {
            JsonObject object = obj.getAsJsonObject();
            departDate = object.get("itineraries.segments.departure.at").getAsString();
            originLocation = object.get("itineraries.segment.departure.iataCode").getAsString();
            destinationLocation = object.get("itineraries.segment.arrival.iataCode").getAsString();
            cabin = object.get("fareDetailsBySegment.cabin").getAsString();
            price = object.get("travelerPricings.price.total").getAsFloat();
        }
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setDepartDate(departDate);
        flightDTO.setOriginLocation(originLocation);
        flightDTO.setDestinationLocation(destinationLocation);
        flightDTO.setCabin(cabin);
        flightDTO.setPrice(price);

        return flightDTO;

    }*/



}

