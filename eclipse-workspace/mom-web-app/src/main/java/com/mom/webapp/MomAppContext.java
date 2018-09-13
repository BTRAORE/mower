/**
 * 
 */
package com.mom.webapp;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mom.webapp.dao.BrandRepository;
import com.mom.webapp.dao.DecorationRepository;
import com.mom.webapp.dao.DurationRepository;
import com.mom.webapp.dao.FurnitureRepository;
import com.mom.webapp.dao.ItemStateRepository;
import com.mom.webapp.dao.ItemsCatalogRepository;
import com.mom.webapp.dao.MealRepository;
import com.mom.webapp.dao.RoomTextileRepository;
import com.mom.webapp.dao.SecurityRepository;
import com.mom.webapp.dao.ToiletRepository;
import com.mom.webapp.dao.WalkRepository;
import static com.mom.webapp.utils.SubCategoryEnum.*;

/**
 * @author Brehima
 *
 */
@Component
public class MomAppContext implements InitializingBean{
	
	 @Autowired
	 private ServletContext servletContext;
	 @Autowired
	 private FurnitureRepository furnitureRepo;
	 @Autowired
	 private DecorationRepository decorationRepo;
	 @Autowired
	 private RoomTextileRepository roomTextileRepo;
	 @Autowired
	 private MealRepository mealRepo;
	 @Autowired
	 private ToiletRepository toiletRepo;
	 @Autowired
	 private SecurityRepository SecurityRepo;
	 @Autowired
	 private WalkRepository walkRepo;
	 @Autowired
	 private ItemStateRepository itemStateRepo;
	 @Autowired
	 private BrandRepository brandRepo;
	 @Autowired
	 private ItemsCatalogRepository itemsCatalogRepo;
	 @Autowired
	 private DurationRepository durationRepo;
	@Override
	public void afterPropertiesSet() throws Exception {
//		servletContext.setAttribute("furnitures", furnitureRepo.findAll());
//		servletContext.setAttribute("decorations", decorationRepo.findAll());
//		servletContext.setAttribute("roomTextiles", roomTextileRepo.findAll());
//		servletContext.setAttribute("cooking", mealRepo.findAll());
//		servletContext.setAttribute("toilets", toiletRepo.findAll());
//		servletContext.setAttribute("securities", SecurityRepo.findAll());
//		servletContext.setAttribute("walks", walkRepo.findAll());
		servletContext.setAttribute("itemStates", itemStateRepo.findAll());
		servletContext.setAttribute("durations", durationRepo.findAll());
//		brand
		servletContext.setAttribute("roomBrands", brandRepo.findByCategoryCategoryNameEquals("room"));
		servletContext.setAttribute(FURNITURES.toString(), itemsCatalogRepo.findBySubCategoryScName(FURNITURES.toString()));
		servletContext.setAttribute(TEXTILES.toString(), itemsCatalogRepo.findBySubCategoryScName(TEXTILES.toString()));
		servletContext.setAttribute(DECORATIONS.toString(), itemsCatalogRepo.findBySubCategoryScName(DECORATIONS.toString()));
		servletContext.setAttribute(TOILETS.toString(), itemsCatalogRepo.findBySubCategoryScName(TOILETS.toString()));
		servletContext.setAttribute(COOKING.toString(), itemsCatalogRepo.findBySubCategoryScName(COOKING.toString()));
		servletContext.setAttribute(WALKING.toString(), itemsCatalogRepo.findBySubCategoryScName(WALKING.toString()));
		servletContext.setAttribute(SECURITIES.toString(), itemsCatalogRepo.findBySubCategoryScName(SECURITIES.toString()));
		servletContext.setAttribute(AWAKENING.toString(), itemsCatalogRepo.findBySubCategoryScName(AWAKENING.toString()));
		servletContext.setAttribute(OUTDOORS.toString(), itemsCatalogRepo.findBySubCategoryScName(OUTDOORS.toString()));
		servletContext.setAttribute(DISCOVERIES.toString(), itemsCatalogRepo.findBySubCategoryScName(DISCOVERIES.toString()));
		servletContext.setAttribute(PUZZLES.toString(), itemsCatalogRepo.findBySubCategoryScName(PUZZLES.toString()));
		itemsCatalogRepo.findBySubCategoryScName(BOARDS.toString()).forEach(item->System.out.println("******************** boards : "+item.getItemName()));
		servletContext.setAttribute(CARS.toString(), itemsCatalogRepo.findBySubCategoryScName(CARS.toString()));
		servletContext.setAttribute(MEDIAS.toString(), itemsCatalogRepo.findBySubCategoryScName(MEDIAS.toString()));
		servletContext.setAttribute(IMAGINARIES.toString(), itemsCatalogRepo.findBySubCategoryScName(IMAGINARIES.toString()));
		servletContext.setAttribute(DOLLS.toString(), itemsCatalogRepo.findBySubCategoryScName(DOLLS.toString()));
		servletContext.setAttribute(BOARDS.toString(), itemsCatalogRepo.findBySubCategoryScName(BOARDS.toString()));
		servletContext.setAttribute(BUILDING.toString(), itemsCatalogRepo.findBySubCategoryScName(BUILDING.toString()));
		servletContext.setAttribute(MUSIC_INSTRUMENTS.toString(), itemsCatalogRepo.findBySubCategoryScName(MUSIC_INSTRUMENTS.toString()));
		servletContext.setAttribute(SHOES.toString(), itemsCatalogRepo.findBySubCategoryScName(SHOES.toString()));
		servletContext.setAttribute(WEARS.toString(), itemsCatalogRepo.findBySubCategoryScName(WEARS.toString()));
		servletContext.setAttribute(UNDERWEARS.toString(), itemsCatalogRepo.findBySubCategoryScName(UNDERWEARS.toString()));
		servletContext.setAttribute(JACKETS.toString(), itemsCatalogRepo.findBySubCategoryScName(JACKETS.toString()));
		servletContext.setAttribute(NIGHT_WEARS.toString(), itemsCatalogRepo.findBySubCategoryScName(NIGHT_WEARS.toString()));
		servletContext.setAttribute(SPORT_WEARS.toString(), itemsCatalogRepo.findBySubCategoryScName(SPORT_WEARS.toString()));
		servletContext.setAttribute(ACCESSORIES.toString(), itemsCatalogRepo.findBySubCategoryScName(ACCESSORIES.toString()));
		servletContext.setAttribute(READING_AND_DVDS.toString(), itemsCatalogRepo.findBySubCategoryScName(READING_AND_DVDS.toString()));
		servletContext.setAttribute(NIGHT_WEARS.toString(), itemsCatalogRepo.findBySubCategoryScName(NIGHT_WEARS.toString()));
		servletContext.setAttribute(PREGNANCY_WEARS.toString(), itemsCatalogRepo.findBySubCategoryScName(PREGNANCY_WEARS.toString()));
		//rent
		servletContext.setAttribute(ROOM_RENT.toString(), itemsCatalogRepo.findBySubCategoryScName(ROOM_RENT.toString()));
		servletContext.setAttribute(TOILET_RENT.toString(), itemsCatalogRepo.findBySubCategoryScName(TOILET_RENT.toString()));
		servletContext.setAttribute(COOKING_RENT.toString(), itemsCatalogRepo.findBySubCategoryScName(COOKING_RENT.toString()));
		servletContext.setAttribute(WALKING_RENT.toString(), itemsCatalogRepo.findBySubCategoryScName(WALKING_RENT.toString()));
		servletContext.setAttribute(GAMING_RENT.toString(), itemsCatalogRepo.findBySubCategoryScName(GAMING_RENT.toString()));
		
	}
}
