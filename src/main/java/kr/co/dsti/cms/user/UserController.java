package kr.co.dsti.cms.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dsti.cms.model.DataTable;


@Controller
public class UserController {
	private final String VIEWS_CREATE_OR_UPDATE_FORM = "user/user_form";

    @Autowired
    UserService service;

    @GetMapping("/user")
    public String index(Pageable pageable, ModelMap model){
        return "user/user_list";
    }

    @GetMapping("/user/new")
    public String initCreationForm(Map<String, Object> model){
        User user = new User();
        model.put("user", user);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/user/new")
    public String processCreationForm(@Validated User user, BindingResult result){
        if (result.hasErrors()) {
            return VIEWS_CREATE_OR_UPDATE_FORM;
        } else {
            this.service.save(user);
            return "redirect:/user/" + user.getId();
        }
    }

    @GetMapping("/user/{userId}")
    public String initUpdateUserForm(@PathVariable("userId") Long userId, ModelMap model) {
        //ModelAndView mav = new ModelAndView("owners/ownerDetails");
        User user = this.service.findById(userId);
        model.addAttribute("user", user);

        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/user/{userId}")
    public String processUpdateUserForm(@Validated User user, BindingResult result, @PathVariable("userId") Long userId) {
        if (result.hasErrors()) {
            return VIEWS_CREATE_OR_UPDATE_FORM;
        } else {
        	user.setId(userId);
            this.service.save(user);
            return "redirect:/user/{userId}";
        }
    }

    @GetMapping("/api/user/list")
    @ResponseBody
    public DataTable ajaxGetList(@RequestParam Integer page, @RequestParam Integer length, @RequestParam Integer draw) {
        Pageable pr = PageRequest.of(page, length, Sort.Direction.DESC, "id");
        DataTable list = this.service.getList(pr, draw);

        return list;
    }

}
