package com.kalanso.event.Controller;

import com.kalanso.event.Model.RolePrestateur;
import com.kalanso.event.Model.RoleUser;
import com.kalanso.event.Service.RolePrestateurServiceImpl;
import com.kalanso.event.Service.Role_Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins="*")
@Controller
@RestController
@RequestMapping("gestEvent/role")
@AllArgsConstructor
public class RoleController {

    private  Role_Service roleService;
    private RolePrestateurServiceImpl rolePrestateur;
    @PostMapping("/AjouterRoles")
    public RoleUser AjouterRole(@RequestBody RoleUser role) {
        return roleService.AjouterRole(role);
    }

    @GetMapping("/listeRole")
    public List<RoleUser> ListeRole() {
        return roleService.ListeRole();
    }



    @DeleteMapping("/supCathegorie/{id}")
    public String SupCategorie(@PathVariable Long id) {
        return roleService.supprimer(id);
    }

    @PutMapping("/modifCategorie/{id}")
    public RoleUser modifierCategorie(@PathVariable Long id, @RequestBody RoleUser role) {
        return roleService.ModifierCategorie(id, role);
    }







    @PostMapping("/AjouterRolesPresta")
    public RolePrestateur AjouterRolePresta(@RequestBody RolePrestateur roles) {
        return rolePrestateur.Ajout(roles);
    }

    @GetMapping("/listeRoPresta")
    public List<RolePrestateur> ListeRolePresta() {
        return rolePrestateur.Afficher();
    }



    @DeleteMapping("/supRolePresta/{id}")
    public String SupRolePresta(@PathVariable Long id) {
        return rolePrestateur.sup(id);
    }

    @PutMapping("/modifRolePresta/{id}")
    public RolePrestateur modifierRolePresta(@PathVariable Long id, @RequestBody RolePrestateur role) {
        return rolePrestateur.MAJ(id,role);
    }




}
