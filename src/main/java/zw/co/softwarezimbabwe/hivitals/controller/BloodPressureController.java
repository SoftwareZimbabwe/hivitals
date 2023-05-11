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
import zw.co.softwarezimbabwe.hivitals.model.BloodPressureDTO;
import zw.co.softwarezimbabwe.hivitals.service.BloodPressureService;
import zw.co.softwarezimbabwe.hivitals.util.WebUtils;


@Controller
@RequestMapping("/bloodPressures")
public class BloodPressureController {

    private final BloodPressureService bloodPressureService;

    public BloodPressureController(final BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("bloodPressures", bloodPressureService.findAll());
        return "bloodPressure/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("bloodPressure") final BloodPressureDTO bloodPressureDTO) {
        return "bloodPressure/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("bloodPressure") @Valid final BloodPressureDTO bloodPressureDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bloodPressure/add";
        }
        bloodPressureService.create(bloodPressureDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bloodPressure.create.success"));
        return "redirect:/bloodPressures";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("bloodPressure", bloodPressureService.get(id));
        return "bloodPressure/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("bloodPressure") @Valid final BloodPressureDTO bloodPressureDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bloodPressure/edit";
        }
        bloodPressureService.update(id, bloodPressureDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bloodPressure.update.success"));
        return "redirect:/bloodPressures";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        bloodPressureService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("bloodPressure.delete.success"));
        return "redirect:/bloodPressures";
    }

}
