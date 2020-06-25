package com.onlineStore.store.controller;

import com.onlineStore.store.domain.*;
import com.onlineStore.store.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ReplyMessageRepo replyMessageRepo;

    @Autowired
    private ProductsBasketRepo productsBasketRepo;

    @Autowired
    private UserRepo userRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("")
    public String products(Model model, @AuthenticationPrincipal User user) {
        Iterable<Product> prod = productRepo.findAll();
        Iterable<String> tags = productRepo.findAllTags();
        Iterable<String> carModels = productRepo.findAllCarModels();

        model.addAttribute("tags", tags);
        model.addAttribute("carModels", carModels);
        model.addAttribute("products", prod);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }
        return "products";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(@AuthenticationPrincipal User user, Model model) {

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }
        return "productCreate";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String add(
            @RequestParam(name="text", required=true, defaultValue="")
                    String text,
            @RequestParam(name="tag", required=true, defaultValue="")
                    String tag,
            @RequestParam(name="carModel", required=true, defaultValue="")
                    String carModel,
            @RequestParam("file")
                    MultipartFile file,
            @RequestParam(name="price", required=true, defaultValue="")
                    float price,
            @AuthenticationPrincipal User user,
            Model model
    ) throws IOException {

        Product product = new Product(tag, carModel, text, price);
        if (file != null && file.getSize() < 60000 && file.getSize() > 0) {
            File dir = new File(uploadPath);

            if (!dir.exists()) {
                dir.mkdir();
            }

            String UUIDFile = UUID.randomUUID().toString();
            String resultFileName = UUIDFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/imgs/" + resultFileName));

            product.setFilename(resultFileName);

        }

        productRepo.save(product);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "redirect:/products";
    }

    @PostMapping("/filter")
    public String filter(
            @RequestParam(name="filterByTag", required=true, defaultValue="")
                    String tag,
            @RequestParam(name="filterByCarModel", required=true, defaultValue="")
                    String carModel,
            @AuthenticationPrincipal User user,
            Model model)
    {
        Iterable<Product> products;
        String text = null;

        if (tag != null && !tag.isEmpty() && (carModel == null || carModel.isEmpty())) {
            products = productRepo.findByTag(tag);
        } else if (tag != null && !tag.isEmpty() && carModel != null && !carModel.isEmpty()) {
            products = productRepo.findByTagAndCarModel(tag, carModel);
        } else {
            products = productRepo.findAll();
        }

        List<Product> list = new ArrayList<>();
        products.forEach(list::add);

        if (list.isEmpty()) {
            text = "По вашему запросу ничего не найдено";
        }

        Iterable<String> tags = productRepo.findAllTags();
        Iterable<String> carModels = productRepo.findAllCarModels();
        model.addAttribute("tags", tags);
        model.addAttribute("carModels", carModels);
        model.addAttribute("text", text);
        model.addAttribute("products", products);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "products";
    }

    @PostMapping("/comment/{product}")
    public String comment(
            @PathVariable String product,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Integer id = Integer.parseInt(product);

        model.addAttribute("product", productRepo.findByIntegerId(id));

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }
        return "productCreateMessage";
    }

    @PostMapping("/reply/{message}")
    public String reply(
            @PathVariable String message,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Integer id = Integer.parseInt(message);

        model.addAttribute("message", messageRepo.findByIntegerId(id));

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "productCreateReplyMessage";
    }

    @PostMapping("/createReply/{msg}")
    public String addReply(
            @RequestParam(name="reply", required=true, defaultValue="")
                    String reply,
            @PathVariable String msg,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Integer id = Integer.parseInt(msg);
        ReplyMessage message = new ReplyMessage(reply, user, messageRepo.findByIntegerId(id));

        replyMessageRepo.save(message);

        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "redirect:/products";
    }

    @PostMapping("/basket/{product}")
    public String addToBasket(
            @PathVariable String product,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Integer id = Integer.parseInt(product);
        ProductsBasket prodBasket = new ProductsBasket(productRepo.findByIntegerId(id), user.getBasket());

        productsBasketRepo.save(prodBasket);

        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "redirect:/products";
    }

    @GetMapping("/basket")
    public String basket(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Basket basket = user.getBasket();
        Iterable<ProductsBasket> productsBasket = productsBasketRepo.findPBByBasketId(basket.getId());
        List<Product> products = new ArrayList<>();

        for (ProductsBasket pb : productsBasket) {
            products.add(pb.getProduct());
        }

        model.addAttribute("products", products);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "basket";
    }

    @PostMapping("/buy/{product}")
    public String buy(
            @PathVariable String product,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Integer productId = Integer.parseInt(product);
        Long basketId = user.getBasket().getId();
        productsBasketRepo.deleteProductFromBasket(basketId, productId);

        Iterable<ProductsBasket> productsBasket = productsBasketRepo.findPBByBasketId(basketId);
        List<Product> products = new ArrayList<>();

        for (ProductsBasket pb : productsBasket) {
            products.add(pb.getProduct());
        }

        model.addAttribute("products", products);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "redirect:/products/basket";
    }



    @PostMapping("/addComment/{product}")
    public String addComment(
            @RequestParam(name="comment", required=true, defaultValue="")
                    String comment,
            @PathVariable String product,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Integer id = Integer.parseInt(product);
        Message message = new Message(comment, user, productRepo.findByIntegerId(id));

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "redirect:/products";
    }

    @PostMapping("/{product}")
    public String get(
            Model model,
            @PathVariable String product,
            @AuthenticationPrincipal User user
    ) {
        Integer id = Integer.parseInt(product);
        Iterable<Message> messages = messageRepo.findForProduct(id);

        for (Message msg : messages) {
            Integer mid = msg.getId();
            List<ReplyMessage> replyMessages = replyMessageRepo.findForMessage(mid);
            msg.setReplies(replyMessages);
        }

        model.addAttribute("product", productRepo.findByIntegerId(id));
        model.addAttribute("messages", messages);

        if (user != null) {
            model.addAttribute("username", user.getUsername());

            if (user.getRoles().contains(Role.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

        return "productMessages";
    }
}

