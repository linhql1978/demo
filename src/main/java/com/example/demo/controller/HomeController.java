package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.AccountRoleService;
import com.example.demo.service.AccountService;
import com.example.demo.service.RoleService;
import com.example.demo.utils.Collage;
import com.example.demo.utils.EncrytedPasswordUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;

@Controller
public class HomeController {

    @Autowired
    ApplicationContext ctx;

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(path = "/userInfo", method = RequestMethod.GET)
    public String userInfo() {

        return "userInfo";
    }

    @RequestMapping(path = "/adminInfo", method = RequestMethod.GET)
    public String adminInfo() {

        return "adminInfo";
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home() {

        return "home";
    }

    @RequestMapping(path = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessful() {

        return "index";
    }

    @RequestMapping(path = "/403", method = RequestMethod.GET)
    public String page403() {

        return "403Page";
    }

    @RequestMapping(path = "/addPhone", method = RequestMethod.GET)
    public String addPhoneForm(Model model) {
        model.addAttribute("phone", new Phone());
        model.addAttribute("phoneDetail", new PhoneDetail());
        model.addAttribute("phoneCategorize", new PhoneCategorize());
        model.addAttribute("collage", new Collage());

        return "addPhone";
    }

    @PostMapping(path = "/addPhone")
    public String addPhoneSubmit(@ModelAttribute Phone phone,
                                 @ModelAttribute PhoneDetail phoneDetail,
                                 @ModelAttribute PhoneCategorize phoneCategorize,
                                 @ModelAttribute Collage collage,Model model) throws IOException {
        //test
//        System.out.println("phoneName= "+phone.getName());
//        System.out.println(file);
        System.out.println(collage.getImage());
        model.addAttribute("collage", collage);
        byte[] bytes = StreamUtils.copyToByteArray(collage.getImage().getInputStream());
        String base64Encoder = Base64.encodeBase64String(bytes);
        model.addAttribute("image", base64Encoder);

        return "test";
    }
//    @
//    public String test(){
//
//        return "test";
//    }

    @RequestMapping(path = "/sign-in", method = RequestMethod.GET)
    public String signIn() {

        return "sign-in";
    }

    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public String detail() {

        return "/detail";
    }

//
//
//
//
//

    //

    @GetMapping(path = "fileUpload")
    public String fileUploadForm() {
        return "fileUpload";
    }

    @PostMapping(path = "/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        model.addAttribute("file", file);
//        file.getInputStream()
        byte[] bytes = StreamUtils.copyToByteArray(file.getInputStream());
        String base64Encoder = Base64.encodeBase64String(bytes);
//                = new String(bytes, "UTF-8");
        model.addAttribute("image", base64Encoder);

        return "showFileUpload";
    }


//    @RequestMapping(path = "/invalidSession",method = RequestMethod.GET)
//    public String invalidSession() {
//        return "invalidSession";
//    }
//
//    @RequestMapping(path = "/sessionExpired",method = RequestMethod.GET)
//    public String sessionExpired() {
//        return "sessionExpired";
//    }

//    @RequestMapping(path = "/test",method = RequestMethod.GET)
//    public String test(){
////        System.out.println("Create Account at session: "+ctx.getBean(Account.class));
////        System.out.println("Create AccountRole at Request: "+ctx.getBean(AccountRole.class));
//
//        AccountService accountService = ctx.getBean(AccountService.class);
//        Account account = accountService.getAccountRepository().findAccountByUsername("QuangLinh1");
//        Account account1 = account;
//        account1.setUsername("QuangLinh");
//
//        accountService.getAccountRepository().save(account1);
//
//        return "login";
//    }

//    @RequestMapping(path = "/checkLogin", method = RequestMethod.POST)
//    public String checkLogin() {
////

//        return "home";

//    }

    @RequestMapping(path = "/createUser", method = RequestMethod.GET)
    public String createUser() {
        AccountService accountService = ctx.getBean(AccountService.class);
        AccountRoleService accountRoleService = ctx.getBean(AccountRoleService.class);
        RoleService roleService = ctx.getBean(RoleService.class);

        Account account = new Account();
        account.setAccountRoles(new HashSet<>());
        account.setUsername("QuangLinh");
        account.setPassword(EncrytedPasswordUtils.encrytePassword("12345"));

        Role role = new Role();
        role.setAccountRoles(new HashSet<>());
        role.setName("ROLE_ADMIN");
        Role role1 = new Role();
        role1.setAccountRoles(new HashSet<>());
        role1.setName("ROLE_USER");

        AccountRole accountRole = new AccountRole();
        //test
//        System.out.println("accountRole code= "+accountRole.hashCode());
        AccountRole accountRole1 = new AccountRole();
        //test
//        System.out.println("accountRole1 code= "+accountRole1.hashCode());

        account.getAccountRoles().add(accountRole);
        account.getAccountRoles().add(accountRole1);

        accountRole.setRole(role);
        accountRole.setAccount(account);
        accountRole1.setRole(role1);
        accountRole1.setAccount(account);

        role.getAccountRoles().add(accountRole);
        role1.getAccountRoles().add(accountRole1);

        accountService.getAccountRepository().save(account);
        //test
//        System.out.println("Size account.accountRoles= "+account.getAccountRoles().size());
//        System.out.println(role.getAccountRoles().size());

//        roleService.getRoleRepository().save(role);
//        roleService.getRoleRepository().save(role1);
//        System.out.println("Size account.accountRoles= "+account.getAccountRoles().size());
//
//        roleService.getRoleRepository().save(role);
//        System.out.println("Size role.accountRoles= "+role.getAccountRoles().size());
//
//        roleService.getRoleRepository().save(role1);
//        System.out.println("Size role1.accountRole= "+role1.getAccountRoles().size());

//        accountRoleService.getAccountRoleRepository().save(accountRole);
//        accountRoleService.getAccountRoleRepository().save(accountRole1);

        return "login";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteAccountRole(@RequestParam Long id) {
        AccountRoleService accountRoleService = ctx.getBean(AccountRoleService.class);
        accountRoleService.getAccountRoleRepository().deleteById(id);

        return "login";
    }

}
