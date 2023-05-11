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
import zw.co.softwarezimbabwe.hivitals.model.BloodGlucoseDTO;
import zw.co.softwarezimbabwe.hivitals.service.BloodGlucoseService;
import zw.co.softwarezimbabwe.hivitals.util.WebUtils;


@Controller
@RequestMapping("/bloodGlucoses")
public class BloodGlucoseController {

    private final BloodGlucoseService bloodGlucoseService;

    public BloodGlucoseController(final BloodGlucoseService bloodGlucoseService) {
        this.bloodGlucoseService = bloodGlucoseService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("bloodGlucoses", bloodGlucoseService.findAll());
        return "bloodGlucose/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("bloodGlucose") final BloodGlucoseDTO bloodGlucoseDTO) {
        return "bloodGlucose/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bloodGlucose") @Valid final BloodGlucoseDTO bloodGlucoseDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bloodGlucose/add";
        }
        bloodGlucoseService.create(bloodGlucoseDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bloodGlucose.create.success"));
        return "redirect:/bloodGlucoses";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("bloodGlucose", bloodGlucoseService.get(id));
        return "bloodGlucose/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("bloodGlucose") @Valid final BloodGlucoseDTO bloodGlucoseDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bloodGlucose/edit";
        }
        bloodGlucoseService.update(id, bloodGlucoseDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bloodGlucose.update.success"));
        return "redirect:/bloodGlucoses";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        bloodGlucoseService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("bloodGlucose.delete.success"));
        return "redirect:/bloodGlucoses";
    }

}
