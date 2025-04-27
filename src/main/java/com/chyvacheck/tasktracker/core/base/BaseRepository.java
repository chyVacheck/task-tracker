
/**
 * @file BaseRepository.java
 * 
 * @extends BaseModule
 * 
 * @description
 * Абстрактный базовый класс для всех репозиториев приложения.
 * Наследуется от BaseModule и устанавливает тип модуля как REPOSITORY.
 * 
 * @details
 * Репозитории отвечают за доступ к данным:
 * - Работа с базой данных или внешними источниками данных
 * - CRUD-операции
 * - Маппинг данных между слоями
 * 
 * Основные задачи репозиториев:
 * - Изоляция слоя доступа к данным от бизнес-логики
 * - Централизация работы с источниками данных
 * 
 * Пример наследования:
 * public class TaskRepository extends BaseRepository
 * 
 * @author
 * Dmytro Shakh
 */

 package com.chyvacheck.tasktracker.core.base;

 /**
  * ! my imports
  */
 import com.chyvacheck.tasktracker.core.system.ModuleType;
 
 /**
  * Абстрактный базовый класс для всех репозиториев приложения.
  */
 public abstract class BaseRepository extends BaseModule {
 
	 /**
	  * Конструктор базового репозитория.
	  * Устанавливает тип модуля как REPOSITORY.
	  */
	 public BaseRepository() {
		 super(ModuleType.REPOSITORY);
	 }
 }