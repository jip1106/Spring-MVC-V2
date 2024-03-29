package hello.itemservice.web.form;

import hello.itemservice.domain.item.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
@Slf4j
public class FormItemController {

    private final ItemRepository itemRepository;


    //자동으로 모델에 담기게 해준다.
    @ModelAttribute("regions")
    public Map<String, String> regions(){
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL","서울");
        regions.put("BUSAN","부산");
        regions.put("JEJU","제주");

        return regions;
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes(){

        /*
             @ModelAttribute 가 있는 deliveryCodes() 메서드는 컨트롤러가 호출 될 때 마다 사용되므로
            deliveryCodes 객체도 계속 생성된다. 이런 부분은 미리 생성해두고 재사용하는 것이 더 효율적이다.

        * */
        /*
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST","빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL","일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW","느린 배송"));

         */

        return SetDeliveryCode.getCodeList();
    }



    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes(){
        //ItemType[] values = ItemType.values();    -> ENUM을 배열로 바꿔줌
        //return values;

        return ItemType.values();
    }


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());

        /*
        @ModelAttribute("regions") 를 사용해서 FormItemController 모든 함수의 model 에 적용시킬 수 있음

        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL","서울");
        regions.put("BUSAN","부산");
        regions.put("JEJU","제주");

        model.addAttribute("regions",regions);
       */

        return "form/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {

        log.info("item.open={}", item.getOpen());
        log.info("item.regions={}", item.getRegions());
        log.info("item.itemType={}",item.getItemType());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/form/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {

        itemRepository.update(itemId, item);

        return "redirect:/form/items/{itemId}";
    }

}

