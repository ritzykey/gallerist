# Spring Boot Project

Bu proje, **Spring ekosisteminin temel bileşenlerini** öğrenmek ve uygulamak amacıyla geliştirilmiştir. Proje kapsamında aşağıdaki teknolojiler ve kavramlar kullanılmıştır:

## 🚀 Kullanılan Teknolojiler
- **Spring Core** – Bağımlılık enjeksiyonu, IoC Container
- **Spring REST API** – RESTful servislerin geliştirilmesi
- **Spring Data MongoDB** – NoSQL veritabanı işlemleri
- **Spring Data JPA** – ORM, veri tabanı işlemleri
- **Spring Validation** – Request body ve parametre validasyonları
- **Spring Security + JWT** – Kimlik doğrulama ve yetkilendirme
- **Spring Pagination** – Sayfalama işlemleri
- **JUnit 5** – Birim testler

## 📂 Proje Yapısı
```
src
┣ main
┃ ┣ java/com/example/project
┃ ┃ ┣ controller # REST controller katmanı
┃ ┃ ┣ service # İş mantığı (business logic)
┃ ┃ ┣ repository # MongoDB repository arayüzleri
┃ ┃ ┗ model # Document sınıfları
┃ ┗ resources
┃ ┗ application.properties # Konfigürasyonlar
┗ test
┗ java/com/example/project # JUnit 5 testleri
```


## 🔑 Özellikler
- Kullanıcı CRUD işlemleri (create, read, update, delete)
- JWT tabanlı authentication & authorization
- Validasyon örnekleri
- Sayfalama ve sıralama desteği
- Birim test örnekleri

## ⚙️ Kurulum & Çalıştırma
1. Bu projeyi klonlayın:
   ```bash
   git clone https://github.com/kullaniciadi/proje-adi.git
2. Proje klasörüne gidin:
    ```bash
    cd proje-adi
3. Maven ile build alın:
    ```bash
    mvn clean install
3. Uygulamayı çalıştırın:
    ```bash
    mvn spring-boot:run
    
🎯 Amaç

Bu proje, Udemy üzerinde tamamladığım
Spring Core | Spring Rest API | Spring Data JPA | Spring Validation | JUnit5 | Spring Security + JWT | Spring Pagination
kursu kapsamında öğrenilen konuları pratiğe dökmek için hazırlanmıştır.

[Türkiye'nin En Güncel Spring Kursu: 2025](https://www.udemy.com/share/10bZqJ/)
Created by [Enes Bayram](https://www.udemy.com/user/enes-bayram-4/)


