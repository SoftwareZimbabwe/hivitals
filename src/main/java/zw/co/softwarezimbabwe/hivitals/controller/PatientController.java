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
import zw.co.softwarezimbabwe.hivitals.model.PatientDTO;
import zw.co.softwarezimbabwe.hivitals.service.PatientService;
import zw.co.softwarezimbabwe.hivitals.util.WebUtils;


@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(final PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "patient/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("patient") final PatientDTO patientDTO) {
        return "patient/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("patient") @Valid final PatientDTO patientDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("patientNumber") && patientDTO.getPatientNumber() != null && patientService.patientNumberExists(patientDTO.getPatientNumber())) {
            bindingResult.rejectValue("patientNumber", "Exists.patient.patientNumber");
        }
        if (bindingResult.hasErrors()) {
            return "patient/add";
        }
        patientService.create(patientDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("patient.create.success"));
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("patient", patientService.get(id));
        return "patient/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("patient") @Valid final PatientDTO patientDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final PatientDTO currentPatientDTO = patientService.get(id);
        if (!bindingResult.hasFieldErrors("patientNumber") && patientDTO.getPatientNumber() != null &&
                !patientDTO.getPatientNumber().equalsIgnoreCase(currentPatientDTO.getPatientNumber()) &&
                patientService.patientNumberExists(patientDTO.getPatientNumber())) {
            bindingResult.rejectValue("patientNumber", "Exists.patient.patientNumber");
        }
        if (bindingResult.hasErrors()) {
            return "patient/edit";
        }
        patientService.update(id, patientDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("patient.update.success"));
        return "redirect:/patients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        patientService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("patient.delete.success"));
        return "redirect:/patients";
    }

}
