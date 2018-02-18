// noinspection JSAnnotator
var bookstoreFrontPageMaster = {
        currentPage: 1,
        pagesCount: 1,
        books: "",
        printPaginationBar: function () {
            var currentPage = parseInt(this.currentPage);
            var pagesCount = parseInt(this.pagesCount);

            $buttonsContainer = $("#paginationNavbar");

            switch (currentPage) {

                // или одна кнопка "1" или кнопки "1" "+" "..." "последняя"
                case(1): {
                    if (pagesCount == 1) {

                        $buttonsContainer.append(this.createNavigationLi(currentPage, currentPage, true));

                    } else {

                        $buttonsContainer.append(this.createNavigationLi(currentPage, currentPage, true));
                        $buttonsContainer.append(this.createNavigationLi("+", (currentPage + 1), false));
                        $buttonsContainer.append(this.createNavigationLi("..."));
                        $buttonsContainer.append(this.createNavigationLi(pagesCount, pagesCount, false));

                    }
                    break;
                }

                case(pagesCount): {

                    $buttonsContainer.append(this.createNavigationLi("1", 1, false));
                    $buttonsContainer.append(this.createNavigationLi("..."));
                    $buttonsContainer.append(this.createNavigationLi("-", (currentPage - 1), false));
                    $buttonsContainer.append(this.createNavigationLi(currentPage, currentPage, true));

                    break;
                }

                default: {

                    $buttonsContainer.append(this.createNavigationLi("1", 1, false));
                    $buttonsContainer.append(this.createNavigationLi("..."));
                    $buttonsContainer.append(this.createNavigationLi("-", (currentPage - 1), false));
                    $buttonsContainer.append(this.createNavigationLi(currentPage, (currentPage), true));
                    $buttonsContainer.append(this.createNavigationLi("+", (currentPage + 1), false));
                    $buttonsContainer.append(this.createNavigationLi("..."));
                    $buttonsContainer.append(this.createNavigationLi(pagesCount, pagesCount, false));

                    break;
                }
            }

        },
        /**
         * Создаёт jQuery элемент списка для панели навигации.
         *
         * @param text          - текст в элементе
         * @param pageNumber    - номер страницы
         * @param active        - выбран ли элемент или нет
         * @returns {*}         - возвращает jQuery элемент списка li
         */
        createNavigationLi: function (text, pageNumber, active) {

            var $resultLi;

            if (!pageNumber) {

                var $dotsA = $("<a>").text(text);
                $resultLi = $("<li disabled=\"true\">").append($dotsA);

            } else {
                var $pageA = $("<a href='/showPage/" + pageNumber + "'>").text(text);
                $resultLi = $("<li>").append($pageA);

                if (active) {
                    $resultLi.attr("class", "active");
                }
            }

            return $resultLi;
        },


        /**
         * Заполняет таблицу Books данными, полученными из json
         *
         * @param books - массив json-ов - объектов Book
         */
        fillTable: function () {
                $(this.books).each(function (index, book) {

                    var previewCommand = "/previewBook/" + book.id;
                    var deleteCommand = "/deleteBook/" + book.id;

                    var $bookTitle = $("<h4>").text(book.title + " - " + book.author);
                    var $titleDiv = $("<td class=\"col-md-8\">").append($bookTitle);

                    var $viewButton = $("<a class=\"btn btn-default\" href=" + previewCommand + ">").text("Preview");
                    var $viewButtonDiv = $("<td class=\"col-md-2\">").append($viewButton);

                    var $deleteButton = $("<a class=\"btn btn-danger\" href=" + deleteCommand + ">").text("Delete Book");
                    var $deleteButtonDiv = $("<td class=\"col-md-2\">").append($deleteButton);

                    var $tableRow = $("<tr>");
                    $tableRow.append($titleDiv);
                    $tableRow.append($viewButtonDiv);
                    $tableRow.append($deleteButtonDiv);

                    var $tableContainer = $("#tableContainer");
                    $tableContainer.append($tableRow);
                })
            },
    }
;
