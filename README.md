<!-- LOGO -->
<br />
<div align="center">
  <a href="https://github.com/ditramadia/IF2210_TB2_NGE">
    <img src="public/gj-logo.png" alt="Logo" width="80"/>
  </a>

<h3 align="center">GANGGUAN JAWA</h3>

  <p align="center">
    Tugas Besar 2
    <br />    
    IF2210 Object Oriented Programming
    <br />
    <a href="https://github.com/ditramadia/Tucil3_13521005_13521019/blob/main/doc/Tucil3_13521005_13521019.pdf"><strong>Laporan »</strong></a>
    <br />
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
        <a href="#about-the-project">About The Project</a>
        <ul>
            <li><a href="#specification">Features</a></li>
            <li><a href="#built-with">Built With</a></li>
            <li><a href="#project-structure">Project Structure</a></li>
        </ul>
    </li>
    <li>
        <a href="#getting-started">Getting Started</a>
        <ul>
            <li><a href="#prerequisites">Prerequisites</a></li>
            <li><a href="#installation">Installation</a></li>
        </ul>
    </li>
    <li>
        <a href="#usage">Usage</a>
    </li>
    <li>
        <a href="#authors">Author</a>
    </li>
  </ol>
</details>
<br/>
<br/>

<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://github.com/ditramadia/Tucil3_13521005_13521019)

Tubes 2 of Object Oriented Programming (IF2210). A point of sales desktop application built with java.

### Features

* Homepage with digital clock using thread
* Transaction records management
* Inventory management system
* Customers management system
* Datastore setting
* Multiple database format options (JSON, XML, and OBJ)

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

### Project Structure
```ssh
.
├─── README.md
├─── public                                 # Public assets
├─── saves                                  # Default database directory
├─── src                                    # Source code
│    ├── asset                              # Images
│    └── main
│        └─── java
│            └─── com.example.if2210_tb2_nge
│                ├─── adapter               # Database adapter
│                ├─── components            # GUI components
│                ├─── controller            # Logic
│                ├─── entity                # Entities
│                ├─── pages                 # GUI pages
│                ├─── repository            # Database repository
│                ├─── style                 # GUI stylesheet
│                └─── NgeApp.java           # Main application
├─── src                                    # Binary files
└───doc                                     # Documentation
```
<br/>
<br/>

<!-- GETTING STARTED -->
## Getting Started

### Dependencies
* JVM
* JavaFX
* projectlombok.lombok
* google.code.gson
* googlecode.json.simple
* itest.json.simple.parser
* jackson-dataformat-xml

### Installation
1. Clone the repo
   ```sh
   git clone https://github.com/ditramadia/IF2210_TB2_NGE
   ```
2. Go to the repository root folder `IF2210_TB2_NGE`
   ```sh
   cd IF2210_TB2_NGE
   ```
3. Run the program `NgeApp.jar`
   ```sh
   java NgeApp
   ```
<br/>
<br/>

<!-- USAGE -->
## Usage

### Inventory Management
1. Inventory
   <br/>
   <br/> <img src="public/inventory-page.png" alt="Inventory page" width="1280">
   <br/>
   <br/>
2. View item detail
   <br/>
   <br/> <img src="public/view-item-detail.png" alt="Item detail page" width="1280">
   <br/>
   <br/>
3. Edit item detail
   <br/>
   <br/> <img src="public/edit-item.png" alt="Add item page" width="1280">
   <br/>
   <br/>
4. Add item
   <br/>
   <br/> <img src="public/add-item.png" alt="Edit item page" width="1280">
   <br/>
   <br/>

### Customer Management
1. Customers list
   <br/>
   <br/> <img src="public/customer-list.png" alt="Customers list page" width="1280">
   <br/>
   <br/>
2. View customer profile
   <br/>
   <br/> <img src="public/view-customer-detail.png" alt="View customer detail" width="1280">
   <br/>
   <br/>
3. Edit customer profile
   <br/>
   <br/> <img src="public/edit-customer.png" alt="Edit customer page" width="1280">
   <br/>
   <br/>
4. View customer purchase history
   <br/>
   <br/> <img src="public/customer-history.jpg" alt="History page" width="1280">
   <br/>
   <br/>
5. Add new customer
   <br/>
   <br/> <img src="public/add-customer.jpg" alt="Add new customer page" width="1280">
   <br/>
   <p>*You can only add customer after adding a transaction record. See Transaction Records Management</p>
   <br/>
   <br/>

### Transaction Records Management
1. Add new transaction record
   <br/>
   <br/> <img src="public/menu.jpg" alt="Menu page" width="1280">
   <br/>
   <br/> <img src="public/card.jpg" alt="Cart page" width="1280">
   <br/>
   <br/> <img src="public/checkout.jpg" alt="Checkout page" width="1280">
   <br/>
   <br/> <img src="public/payment-success.jpg" alt="Success page" width="1280">
   <br/>
   <br/>

### Database Settings
1. Database directory and format
   <br/>
   <br/> <img src="public/setting.jpg" alt="Setting" width="1280">
   <br/>
   <br/>


<!-- AUTHOR -->

## Authors

|   NIM    |          Name          |
|:--------:|:----------------------:|
| 13521005 | Kelvin Rayhan Alkarim  |  
| 13521019 |   Ditra Rizqa Amadia   |
| 13521020 | Varraz Hazzandra Akbar |
| 13521021 |   Bernardus Willson    |
| 13521022 |  Raditya Naufal Abiyu  |
| 13521023 |  Kenny Benaya Nathan   |

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[product-screenshot]: public/home-page.png
