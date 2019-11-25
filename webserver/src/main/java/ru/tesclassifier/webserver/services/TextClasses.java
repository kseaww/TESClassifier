package ru.tesclassifier.webserver.services;

public enum TextClasses {
    alchemical_ingredients,
    armors,
    artifacts,
    attributes,
    books,
    characters,
    creatures,
    deities,
    diseases,
    events,
    fractions,
    items,
    locations,
    magic,
    modifications,
    nations,
    others,
    quests,
    race,
    skills,
    weapons;
    
    public static String getTextClassRusName(TextClasses textClass) {
        switch (textClass) {
            case alchemical_ingredients:
                return "Алхимические ингредиенты";
            case armors:
                return "Доспехи";
            case artifacts:
                return "Артефакты";
            case attributes:
                return "Атрибуты";
            case books:
                return "Книги";
            case characters:
                return "Персонажи";
            case creatures:
                return "Существа";
            case deities:
                return "Божества";
            case diseases:
                return "Заболевания";
            case events:
                return "События";
            case fractions:
                return "Фракции";
            case items:
                return "Предметы";
            case locations:
                return "Локации";
            case magic:
                return "Магия";
            case modifications:
                return "Модификации";
            case nations:
                return "Народы";
            case others:
                return "Другое";
            case quests:
                return "Квесты";
            case race:
                return "Расы";
            case skills:
                return "Навыки";
            case weapons:
                return "Оружие";
            default:
                return "";
        }
    }
}
