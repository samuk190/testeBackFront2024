# Use uma imagem oficial do PHP com Apache
FROM php:8.2-apache

# Instale extensões necessárias do PHP
RUN apt-get update && apt-get install -y \
    zip unzip curl libpng-dev libjpeg-dev libfreetype6-dev libonig-dev libxml2-dev \
    && docker-php-ext-install pdo pdo_mysql mbstring gd


    RUN apt-get update && apt-get install -y coreutils
# Instale o Composer
COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

# Copie os arquivos do Laravel para o contêiner
COPY . /var/www/html
COPY wait-for-it.sh /app/wait-for-it.sh
WORKDIR /app
RUN chmod +x wait-for-it.sh
# Configure permissões apropriadas
RUN chown -R www-data:www-data /var/www/html \
    && chmod -R 775 /var/www/html/storage /var/www/html/bootstrap/cache

# Ative o mod_rewrite do Apache
RUN a2enmod rewrite

# Configure o diretório de trabalho
WORKDIR /var/www/html

# Instale as dependências do Laravel
RUN composer install --optimize-autoloader --no-dev

# Expõe a porta 8000
EXPOSE 8000

# Comando para iniciar o servidor
CMD ["php", "artisan", "serve", "--host=0.0.0.0", "--port=8000"]
