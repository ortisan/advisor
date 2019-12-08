/**
 * Theme: Drezoc - Bootstrap 4 Admin Template
 * Author: Myra Studio
 * File: Main Js
 */


(function ($) {

    'use strict';

    function initSlimscrollMenu() {
        $('.custom-scroll').slimscroll({
            height: 'auto',
            position: 'right',
            size: "6px",
            color: '#929cab',
            touchScrollStep: 50
        });
    }

    function initDropdownMenu() {
        $('.dropdown-menu a.dropdown-toggle').on('click', function(e) {
            if (!$(this).next().hasClass('show')) {
              $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
            }
            var $subMenu = $(this).next(".dropdown-menu");
            $subMenu.toggleClass('show');
    
            return false;
        });   
    }

    function initActiveMenu() {
        // === following js will activate the menu in left side bar based on url ====
        $(".topnav-menu .navbar-nav a").each(function () {
            var pageUrl = window.location.href.split(/[?#]/)[0];
            if (this.href == pageUrl) {  
                $(this).addClass("active");
                $(this).parent().addClass("active"); // add active to li of the current link
                $(this).parent().parent().addClass("active");
                $(this).parent().parent().parent().parent().addClass("active");
            }
        });
    }

    function initComponents() {
        // Tooltips
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })

        // Popovers
        $(function () {
            $('[data-toggle="popover"]').popover()
        })
    }

    function init() {
        initSlimscrollMenu();
        initDropdownMenu();
        initActiveMenu();
        initComponents();
    }

    init();

})(jQuery)