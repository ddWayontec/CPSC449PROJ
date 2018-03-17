module Parser where
import System.IO  
import Control.Monad
import System.Environment
import Data.List
import Data.String

removeEndSpace :: String -> String
removeEndSpace [] = []
removeEndSpace (x)   | last x == ' ' = removeEndSpace (init x)
                     | last x == '\t' = removeEndSpace (init x)
                     | otherwise = x

--removeStartSpace :: String -> String
--removeStartSpace [] = []
--removeStartSpace (x)   | head x == ' ' = removeStartSpace (tail x)
--                       | head x == '\t' = removeStartSpace (tail x)

--removeBlankLine :: [String] -> [String]
--removeBlankLine (x:xs)  | x /= "" = x:(removeBlankLine xs)
--                       | otherwise = removeBlankLine xs

validMach :: Char -> Bool
validMach a    | a == '1' = True
            | a == '2' = True
            | a == '3' = True
            | a == '4' = True
            | a == '5' = True
            | a == '6' = True
            | a == '7' = True
            | a == '8' = True
            | otherwise	= False

removeBlankLine :: [String] -> [String]
removeBlankLine (x:xs) = [ x | x <- xs, not (null x) ]

keepFromTo :: String -> String -> [String] -> [String]
keepFromTo _ _ [] = []
keepFromTo s e a = cutFront s (cutEnd e a)

cutEnd :: String -> [String] -> [String]
cutEnd _ [] = []
cutEnd w (x:xs)   | x /= w = x:(cutEnd w xs)
                  | otherwise = []

cutFront :: String -> [String] -> [String]
cutFront _ [] = []
cutFront w (x:xs)   | x /= w = cutFront w xs
                    | otherwise = xs

deleteR :: String -> String
deleteR [] = []
deleteR (x:xs)  | x == '\r' = deleteR xs
                | otherwise = x:(deleteR xs)