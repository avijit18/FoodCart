package com.FoodCart.Services.IMPL;

import com.FoodCart.DTOs.RestaurantDTO;
import com.FoodCart.Entities.Address;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Repositories.AddressRepository;
import com.FoodCart.Repositories.RestaurantRepository;
import com.FoodCart.Repositories.UserRepository;
import com.FoodCart.Requests.CreateRestaurantRequest;
import com.FoodCart.Services.Interfaces.RestaurantService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImplementation implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserService userService;


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, UserEntity user) {
        Address address = new Address();
        address.setCity(req.getAddress().getCity());
        address.setCountry(req.getAddress().getCountry());
        address.setFullName(req.getAddress().getFullName());
        address.setPostalCode(req.getAddress().getPostalCode());
        address.setState(req.getAddress().getState());
        address.setStreetAddress(req.getAddress().getStreetAddress());
        Address savedAddress = addressRepository.save(address);

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(savedAddress);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(req.getRegistrationDate());
        restaurant.setOwner(user);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return savedRestaurant;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
            throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (restaurant != null) {
            restaurantRepository.delete(restaurant);
            return;
        }
        throw new RestaurantException("Restaurant with id " + restaurantId + " Not found");
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws RestaurantException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new RestaurantException("Restaurant with id " + id + "not found");
        }
    }

    @Override
    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException {
        Restaurant restaurants = restaurantRepository.findByOwnerId(userId);
        return restaurants;
    }

    @Override
    public RestaurantDTO addToFavorites(Long restaurantId, UserEntity user)
            throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDTO dto = new RestaurantDTO();
        dto.setTitle(restaurant.getName());
        dto.setImages(restaurant.getImages());
        dto.setId(restaurant.getId());
        dto.setDescription(restaurant.getDescription());

        boolean isFavorited = false;
        List<RestaurantDTO> favorites = user.getFavorites();
        for (RestaurantDTO favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }
        if (isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(dto);
        }
        UserEntity updatedUser = userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws RestaurantException {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
