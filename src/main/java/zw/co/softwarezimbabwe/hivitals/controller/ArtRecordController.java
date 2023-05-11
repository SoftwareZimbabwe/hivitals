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
import zw.co.softwarezimbabwe.hivitals.model.ArtRecordDTO;
import zw.co.softwarezimbabwe.hivitals.service.ArtRecordService;
import zw.co.softwarezimbabwe.hivitals.util.WebUtils;


@Controller
@RequestMapping("/artRecords")
public class ArtRecordController {

    private final ArtRecordService artRecordService;

    public ArtRecordController(final ArtRecordService artRecordService) {
        this.artRecordService = artRecordService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("artRecords", artRecordService.findAll());
        return "artRecord/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("artRecord") final ArtRecordDTO artRecordDTO) {
        return "artRecord/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("artRecord") @Valid final ArtRecordDTO artRecordDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "artRecord/add";
        }
        artRecordService.create(artRecordDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("artRecord.create.success"));
        return "redirect:/artRecords";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("artRecord", artRecordService.get(id));
        return "artRecord/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("artRecord") @Valid final ArtRecordDTO artRecordDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "artRecord/edit";
        }
        artRecordService.update(id, artRecordDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("artRecord.update.success"));
        return "redirect:/artRecords";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        artRecordService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("artRecord.delete.success"));
        return "redirect:/artRecords";
    }

}
