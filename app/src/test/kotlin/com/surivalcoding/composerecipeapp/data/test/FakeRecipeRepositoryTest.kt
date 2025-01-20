package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.model.UserId
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.UUID

class FakeRecipeRepositoryTest {

    @Test
    fun getSavedRecipes() = runTest {
        val repo = FakeRecipeRepository(
            DemoNetworkRecipeDataSource(recipeJson.byteInputStream()),
            userDataSource = DemoNetworkUserDataSource(userJson.byteInputStream())
        )
        println(repo.getSavedRecipes(UserId(UUID.randomUUID())))

    }

    @Test
    fun findBySearchTerm() {
    }

    val userJson = """{
  "getCurrentUser": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "memberSince": "2024-01-20T12:00:00Z",
    "nickname": "chef_mike",
    "fullName": "Michael Johnson",
    "email": "mike.johnson@example.com",
    "profileImage": {
      "type": "image",
      "url": "https://picsum.photos/400/400"
    },
    "following": [
      {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "nickname": "foodie_sara",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/401/401"
        }
      },
      {
        "id": "550e8400-e29b-41d4-a716-446655440002",
        "nickname": "cooking_master",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/402/402"
        }
      },
      {
        "id": "550e8400-e29b-41d4-a716-446655440003",
        "nickname": "bakery_queen",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/403/403"
        }
      }
    ],
    "followers": [
      {
        "id": "550e8400-e29b-41d4-a716-446655440004",
        "nickname": "pasta_lover",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/404/404"
        }
      },
      {
        "id": "550e8400-e29b-41d4-a716-446655440005",
        "nickname": "asian_cuisine",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/405/405"
        }
      },
      {
        "id": "550e8400-e29b-41d4-a716-446655440006",
        "nickname": "healthy_eats",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/406/406"
        }
      },
      {
        "id": "550e8400-e29b-41d4-a716-446655440007",
        "nickname": "dessert_master",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/407/407"
        }
      }
    ],
    "bio": "Passionate about creating delicious recipes",
    "occupation": "Professional Chef",
    "address": "Seattle, WA",
    "savedPosts": [
      "123e4567-e89b-12d3-a456-426614174000",
      "123e4567-e89b-12d3-a456-426614174001",
      "123e4567-e89b-12d3-a456-426614174002",
      "123e4567-e89b-12d3-a456-426614174003"
    ]
  }
}""".trimIndent()

    val recipeJson = """{
  "getRecipes": [
    {
      "id": "123e4567-e89b-12d3-a456-426614174000",
      "author": {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "nickname": "chef_mike",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/400/400"
        }
      },
      "title": "Classic Margherita Pizza",
      "comments": [
        {
          "userId": 1,
          "content": "Great recipe! Love the simplicity.",
          "likes": [],
          "dislike": [],
          "editedAt": "2024-01-19T15:30:00Z",
          "createdAt": "2024-01-19T15:30:00Z"
        }
      ],
      "shareableLink": "https://recipes.example.com/recipe/123e4567-e89b-12d3-a456-426614174000",
      "editedAt": "2024-01-19T14:00:00Z",
      "createdAt": "2024-01-19T14:00:00Z",
      "thumbnail": {
        "type": "image",
        "url": "https://picsum.photos/800/600"
      },
      "media": [
        {
          "type": "image",
          "url": "https://picsum.photos/800/600"
        }
      ],
      "content": {
        "ingredients": [
          {
            "name": "Pizza Dough",
            "shortName": "dough",
            "thumbnail": {
              "type": "image",
              "url": "https://picsum.photos/200/200"
            },
            "amountInGrams": 250
          },
          {
            "name": "Mozzarella",
            "shortName": "mozz",
            "thumbnail": {
              "type": "image",
              "url": "https://picsum.photos/201/201"
            },
            "amountInGrams": 200
          }
        ],
        "instruction": [
          {
            "title": "Prepare the dough",
            "description": "Roll out the pizza dough into a 12-inch circle"
          },
          {
            "title": "Add toppings",
            "description": "Spread sauce and add fresh mozzarella"
          }
        ],
        "starRating": 4.8,
        "cookingTimeInMinutes": 20,
        "servings": 4,
        "tags": [
          "Italian",
          "Pizza",
          "Vegetarian"
        ]
      }
    },
    {
      "id": "123e4567-e89b-12d3-a456-426614174001",
      "author": {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "nickname": "foodie_sara",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/401/401"
        }
      },
      "title": "Spicy Thai Basil Chicken",
      "comments": [
        {
          "userId": 2,
          "content": "Perfect level of spiciness!",
          "likes": [],
          "dislike": [],
          "editedAt": "2024-01-19T16:30:00Z",
          "createdAt": "2024-01-19T16:30:00Z"
        }
      ],
      "shareableLink": "https://recipes.example.com/recipe/123e4567-e89b-12d3-a456-426614174001",
      "editedAt": "2024-01-19T15:00:00Z",
      "createdAt": "2024-01-19T15:00:00Z",
      "thumbnail": {
        "type": "image",
        "url": "https://picsum.photos/801/601"
      },
      "media": [
        {
          "type": "image",
          "url": "https://picsum.photos/801/601"
        }
      ],
      "content": {
        "ingredients": [
          {
            "name": "Chicken",
            "shortName": "chk",
            "thumbnail": {
              "type": "image",
              "url": "https://picsum.photos/202/202"
            },
            "amountInGrams": 500
          },
          {
            "name": "Thai Basil",
            "shortName": "basil",
            "thumbnail": {
              "type": "image",
              "url": "https://picsum.photos/203/203"
            },
            "amountInGrams": 50
          }
        ],
        "instruction": [
          {
            "title": "Prepare chicken",
            "description": "Cut chicken into bite-sized pieces"
          },
          {
            "title": "Stir fry",
            "description": "Cook with basil and sauce until done"
          }
        ],
        "starRating": 4.6,
        "cookingTimeInMinutes": 25,
        "servings": 3,
        "tags": [
          "Thai",
          "Spicy",
          "Chicken"
        ]
      }
    },
    {
      "id": "123e4567-e89b-12d3-a456-426614174002",
      "author": {
        "id": "550e8400-e29b-41d4-a716-446655440002",
        "nickname": "cooking_master",
        "profileImage": {
          "type": "image",
          "url": "https://picsum.photos/402/402"
        }
      },
      "title": "Chocolate Lava Cake",
      "comments": [
        {
          "userId": 3,
          "content": "Decadent and delicious!",
          "likes": [],
          "dislike": [],
          "editedAt": "2024-01-19T17:30:00Z",
          "createdAt": "2024-01-19T17:30:00Z"
        }
      ],
      "shareableLink": "https://recipes.example.com/recipe/123e4567-e89b-12d3-a456-426614174002",
      "editedAt": "2024-01-19T16:00:00Z",
      "createdAt": "2024-01-19T16:00:00Z",
      "thumbnail": {
        "type": "image",
        "url": "https://picsum.photos/802/602"
      },
      "media": [
        {
          "type": "image",
          "url": "https://picsum.photos/802/602"
        }
      ],
      "content": {
        "ingredients": [
          {
            "name": "Dark Chocolate",
            "shortName": "choc",
            "thumbnail": {
              "type": "image",
              "url": "https://picsum.photos/204/204"
            },
            "amountInGrams": 200
          },
          {
            "name": "Butter",
            "shortName": "butter",
            "thumbnail": {
              "type": "image",
              "url": "https://picsum.photos/205/205"
            },
            "amountInGrams": 100
          }
        ],
        "instruction": [
          {
            "title": "Melt chocolate",
            "description": "Melt chocolate and butter together"
          },
          {
            "title": "Bake",
            "description": "Pour into ramekins and bake until edges are set"
          }
        ],
        "starRating": 4.9,
        "cookingTimeInMinutes": 15,
        "servings": 2,
        "tags": [
          "Dessert",
          "Chocolate",
          "Baking"
        ]
      }
    }
  ]
}""".trimIndent()
}