package zw.co.softwarezimbabwe.hivitals.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.softwarezimbabwe.hivitals.model.WeightDTO;
import zw.co.softwarezimbabwe.hivitals.service.WeightService;
import zw.co.softwarezimbabwe.hivitals.util.WebUtils;


@Controller
@RequestMapping("/weights")
public class WeightController {

    private final WeightService weightService;

    public WeightController(final WeightService weightService) {
        this.weightService = weightService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("weights", weightService.findAll());
        return "weight/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("weight") final WeightDTO weightDTO) {
        return "weight/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("weight") @Valid final WeightDTO weightDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "weight/add";
        }
        weightService.create(weightDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("weight.create.success"));
        return "redirect:/weights";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("weight", weightService.get(id));
        return "weight/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("weight") @Valid final WeightDTO weightDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "weight/edit";
        }
        weightService.update(id, weightDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("weight.update.success"));
        return "redirect:/weights";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        weightService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("weight.delete.success"));
        return "redirect:/weights";
    }

}
