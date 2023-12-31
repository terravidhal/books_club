package com.dojo.fullspring.starter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dojo.fullspring.starter.models.Books;
import com.dojo.fullspring.starter.models.User;
import com.dojo.fullspring.starter.service.BooksService;
import com.dojo.fullspring.starter.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class BooksController {

    // injection de dependances 
   @Autowired
    BooksService bookService;

   @Autowired
    UserService userService;



    // home, all books 
    @RequestMapping ("/books")
    public String home(Model model, 
                       HttpSession session){

    	if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }

        Long userId = (Long) session.getAttribute("userId");
        User currentUser = userService.findUserById(userId);
        model.addAttribute("currentUser", currentUser);
        
        List<Books> allBooks = bookService.allBooks();
        model.addAttribute("allBooks", allBooks);

        return "home.jsp";
    	  		
    }


     // Book Details
    @RequestMapping("/books/{id}")
    public String showBook(Model model, 
                           @PathVariable("id") Long id, 
                           HttpSession session) {

		if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
                            
		Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		model.addAttribute("currentUser", currentUser);
		
        Books book = bookService.findBook(id);
        model.addAttribute("book", book);

        return "bookDetails.jsp";
    }



    // form page / create new book
    @RequestMapping("/book/new")
    public String create_page(@ModelAttribute("bookAttr") Books book,
                              Model model,
                              HttpSession session) { 

        Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);

		model.addAttribute("currentUser", currentUser); 
                                
        return "addBook.jsp";
    }


    // processing request create new book
    @RequestMapping(value="/process_book", method=RequestMethod.POST)
     public String create_process(@Valid @ModelAttribute("bookAttr") Books book, 
                                 BindingResult result) {

        if (result.hasErrors()) {
            return "addBook.jsp";
        } 

        bookService.createBook(book);;
        return "redirect:/books";
        
    }



    
    //Edit book:
    @GetMapping("/books/{id}/edit")
     public String edit(@PathVariable("id") Long id, 
                        Model model,
                        HttpSession session) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }

        Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		model.addAttribute("currentUser", currentUser);
        
        
        Books book = bookService.findBook(id); 
        model.addAttribute("bookObj", book);

        return "editBook.jsp";
    }

    
    //Edit process:
    @RequestMapping(value="/process_update/books/{id}", method=RequestMethod.PUT)  // PUT method
    public String update(@Valid @ModelAttribute("bookObj") Books book, 
                          BindingResult result,
                          HttpSession session,
                          Model model) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }        

        if (result.hasErrors()) {
            model.addAttribute("bookObj", book);
            return "editBook.jsp";
        } 

        bookService.updateBook(book); 

        return "redirect:/books";
        
    }

       // processing request deleted specific books
       
      //sans le form
      @RequestMapping("/books/{id}/delete")
      public String destroy(@PathVariable("id") Long id,
                            HttpSession session) {

        if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}

        bookService.deleteBook(id);

        return "redirect:/books";
      }


}
