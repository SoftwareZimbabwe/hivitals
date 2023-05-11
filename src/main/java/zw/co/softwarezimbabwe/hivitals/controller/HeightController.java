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
import zw.co.softwarezimbabwe.hivitals.model.HeightDTO;
import zw.co.softwarezimbabwe.hivitals.service.HeightService;
import zw.co.softwarezimbabwe.hivitals.util.WebUtils;


@Controller
@RequestMapping("/heights")
public class HeightController {

    private final HeightService heightService;

    public HeightController(final HeightService heightService) {
        this.heightService = heightService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("heights", heightService.findAll());
        return "height/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("height") final HeightDTO heightDTO) {
        return "height/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("height") @Valid final HeightDTO heightDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "height/add";
        }
        heightService.create(heightDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("height.create.success"));
        return "redirect:/heights";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("height", heightService.get(id));
        return "height/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("height") @Valid final HeightDTO heightDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "height/edit";
        }
        heightService.update(id, heightDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("height.update.success"));
        return "redirect:/heights";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        heightService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("height.delete.success"));
        return "redirect:/heights";
    }

}
